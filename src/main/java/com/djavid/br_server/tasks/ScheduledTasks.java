package com.djavid.br_server.tasks;

import com.djavid.br_server.model.entity.RegistrationToken;
import com.djavid.br_server.model.entity.cryptonator.CoinMarketCapTicker;
import com.djavid.br_server.model.entity.cryptonator.CoinMarketList;
import com.djavid.br_server.model.entity.cryptonator.CryptonatorTicker;
import com.djavid.br_server.model.repository.RegistrationTokenRepository;
import com.djavid.br_server.push.AndroidPushNotificationsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CompletableFuture;


@Component
public class ScheduledTasks {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    private final static String CRYPTONATOR_URL = "https://api.cryptonator.com/api/full/";
    private final static String COINMARKETCAP_URL = "https://api.coinmarketcap.com/v1/ticker/?convert=";

    private static String[] crypto_coins = {"BTC", "BCH", "LTC", "ETH", "NVC", "NMC", "PPC", "DOGE"};
    private static String[] country_coins = {"USD", "EUR", "CAD", "CNY", "JPY", "PLN", "GBP", "RUB", "UAH"};

    private RegistrationTokenRepository registrationTokenRepository;
    private AndroidPushNotificationsService androidPushNotificationsService;
    private RestTemplate restTemplate;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");


    public ScheduledTasks(RegistrationTokenRepository registrationTokenRepository,
                          AndroidPushNotificationsService androidPushNotificationsService) {
        this.registrationTokenRepository = registrationTokenRepository;
        this.androidPushNotificationsService = androidPushNotificationsService;
        restTemplate = new RestTemplate();
    }

    //@Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        log.info("Sending push notification...");

        RegistrationToken token = registrationTokenRepository.findOne(1L);

        try {
            CompletableFuture<String> pushNotification = androidPushNotificationsService
                    .send(token.token, "Изменение цены Bitcoin", dateFormat.format(new Date()));
            CompletableFuture.allOf(pushNotification).join();
            String firebaseResponse = pushNotification.get();
            log.info(firebaseResponse);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Scheduled(fixedDelay = 30000)
    public void getCurrentRate() {
        //List<CoinMarketList> arrayList = new ArrayList<>();
        List<CoinMarketCapTicker> pairs = new ArrayList<>();

        for (String country : country_coins) {
            CoinMarketCapTicker[] coinMarketList = restTemplate
                    .getForObject(COINMARKETCAP_URL + country, CoinMarketCapTicker[].class);

            for (int i = 0; i < 4; i++) {
                String coin_symbol = crypto_coins[i];
                Arrays.stream(coinMarketList)
                        .filter(ticker -> ticker.getSymbol().equals(coin_symbol))
                        .findFirst()
                        .ifPresent(ticker -> {
                            ticker.setCountry_symbol(country);
                            pairs.add(ticker);
                        });
            }

            //arrayList.add(coinMarketList);
        }

        String summary = "";
        for (CoinMarketCapTicker ticker : pairs) {
            summary += "{" + ticker.getCountry_symbol() + "-" + ticker.getSymbol() + " = " + ticker.getPrice() + "} ";
        }
        log.info(summary);

    }

}
