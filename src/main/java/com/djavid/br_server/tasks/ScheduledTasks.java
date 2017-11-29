package com.djavid.br_server.tasks;

import com.djavid.br_server.BrServerApplication;
import com.djavid.br_server.Codes;
import com.djavid.br_server.model.entity.RegistrationToken;
import com.djavid.br_server.model.entity.Subscribe;
import com.djavid.br_server.model.entity.Ticker;
import com.djavid.br_server.model.entity.cryptonator.CoinMarketCapTicker;
import com.djavid.br_server.model.repository.RegistrationTokenRepository;
import com.djavid.br_server.model.repository.SubscribeRepository;
import com.djavid.br_server.model.repository.TickerRepository;
import com.djavid.br_server.push.AndroidPushNotificationsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import java.util.*;
import java.util.concurrent.CompletableFuture;


@Component
public class ScheduledTasks {

    private final static String CRYPTONATOR_URL = "https://api.cryptonator.com/api/full/";
    private final static String COINMARKETCAP_URL = "https://api.coinmarketcap.com/v1/ticker/?convert=";

    private static String[] crypto_coins = {"BTC", "BCH", "LTC", "ETH", "NVC", "NMC", "PPC", "DOGE"};
    private static String[] country_coins = {"USD", "EUR", "CAD", "CNY", "JPY", "PLN", "GBP", "RUB", "UAH"};

    private RegistrationTokenRepository registrationTokenRepository;
    private SubscribeRepository subscribeRepository;
    private TickerRepository tickerRepository;
    private AndroidPushNotificationsService androidPushNotificationsService;
    private RestTemplate restTemplate;


    public ScheduledTasks(RegistrationTokenRepository registrationTokenRepository,
                          SubscribeRepository subscribeRepository,
                          TickerRepository tickerRepository,
                          AndroidPushNotificationsService androidPushNotificationsService) {
        this.registrationTokenRepository = registrationTokenRepository;
        this.androidPushNotificationsService = androidPushNotificationsService;
        this.subscribeRepository = subscribeRepository;
        this.tickerRepository = tickerRepository;
        restTemplate = new RestTemplate();
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
        for (CoinMarketCapTicker ticker : pairs)
            summary += "{" + ticker.getCountry_symbol() + "-" + ticker.getSymbol() + " = " + ticker.getPrice() + "} ";
        BrServerApplication.log.info(summary);

        Iterable<Subscribe> subscribes = subscribeRepository.findAll();
        subscribes.forEach(subscribe -> {
            Ticker ticker = tickerRepository.findOne(subscribe.getTickerId());
            pairs.stream()
                    .filter(pair -> pair.getCountry_symbol().equals(ticker.getCountryId())
                            && pair.getSymbol().equals(ticker.getCurrId()))
                    .findFirst()
                    .ifPresent(pair -> {
                        if (checkForSending(subscribe, pair)) {
                            sendPush(subscribe, pair);
                            System.out.println(subscribe.toString());
                            subscribeRepository.delete(subscribe); //TODO
                        }
                    });
        });

    }

    private boolean checkForSending(Subscribe subscribe, CoinMarketCapTicker ticker) {
        Double sub_price = Double.parseDouble(subscribe.getValue());
        //System.out.println(sub_price);
        if (subscribe.isTrendingUp()) {
            if (ticker.getPrice() >= sub_price) return true;
        } else {
            if (ticker.getPrice() <= sub_price) return true;
        }

        return false;
    }

    private void sendPush(Subscribe subscribe, CoinMarketCapTicker capTicker) {
        Ticker ticker = tickerRepository.findOne(subscribe.getTickerId());
        RegistrationToken token = registrationTokenRepository.findOne(ticker.getTokenId());
        String curr_full = Codes.getCurrencyFullName(ticker.getCurrId());

        String title = "Изменение цены " + curr_full;
        String desc;

        if (subscribe.isTrendingUp()) {
            desc = "Цена " + curr_full + " выросла до " + String.format("%.2f", capTicker.getPrice()) + " "
                    + ticker.getCountryId() + "!";
        } else {
            desc = "Цена " + curr_full + " упала до " + String.format("%.2f", capTicker.getPrice()) + " "
                    + ticker.getCountryId() + "!";
        }

        BrServerApplication.log.info("Sending push notification with title ='" + title + "' and body = '" + desc + "';");

        try {
            CompletableFuture<String> pushNotification = androidPushNotificationsService
                    .send(token.token, title, desc);
            CompletableFuture.allOf(pushNotification).join();
            String firebaseResponse = pushNotification.get();
            BrServerApplication.log.info(firebaseResponse);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
