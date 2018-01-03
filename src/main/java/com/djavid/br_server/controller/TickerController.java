package com.djavid.br_server.controller;

import com.djavid.br_server.BrServerApplication;
import com.djavid.br_server.Config;
import com.djavid.br_server.model.entity.*;
import com.djavid.br_server.model.entity.exmo.ExmoTicker;
import com.djavid.br_server.model.repository.CurrencyUpdateRepository;
import com.djavid.br_server.model.repository.RegistrationTokenRepository;
import com.djavid.br_server.model.repository.SubscribeRepository;
import com.djavid.br_server.model.repository.TickerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
public class TickerController {

    private final TickerRepository tickerRepository;
    private final SubscribeRepository subscribeRepository;
    private final RegistrationTokenRepository tokenRepository;
    private final CurrencyUpdateRepository currencyUpdateRepository;
    private RestTemplate restTemplate;


    public TickerController(TickerRepository tickerRepository, SubscribeRepository subscribeRepository,
                            RegistrationTokenRepository tokenRepository, CurrencyUpdateRepository currencyUpdateRepository) {
        this.tickerRepository = tickerRepository;
        this.subscribeRepository = subscribeRepository;
        this.tokenRepository = tokenRepository;
        this.currencyUpdateRepository = currencyUpdateRepository;
        this.restTemplate = new RestTemplate();
    }


    @RequestMapping(value = "/getTickers/", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Ticker> getAllTickers() {
        return tickerRepository.findAll();
    }


    @RequestMapping(value = "/getTickers", method = RequestMethod.GET)
    public ResponseTickers getTickersByTokenId(@RequestHeader("Token") String token,
                                               @RequestParam("token_id") long token_id) {

        if (tokenRepository.findRegistrationTokenByToken(token) == null)
            return new ResponseTickers("No such token!");

        RegistrationToken registrationToken = tokenRepository.findRegistrationTokenById(token_id);
        if (registrationToken == null || !registrationToken.getToken().equals(token))
            return new ResponseTickers("Access denied!");
        registrationToken.setLastVisited(System.currentTimeMillis());
        tokenRepository.save(registrationToken);

        Iterable<Ticker> tickers = tickerRepository.getTickersByTokenId(token_id);
        for (Ticker ticker : tickers) {
            CurrencyUpdate currencyUpdate = currencyUpdateRepository
                    .findCurrencyUpdateByCryptoIdAndCountryId(ticker.getCryptoId(), ticker.getCountryId());

            if (currencyUpdate != null)
                ticker.setTicker(currencyUpdate);
        }

        return new ResponseTickers(tickers);
    }

    @RequestMapping(value = "/getTicker", method = RequestMethod.GET)
    public Ticker getTickerByTokenIdAndId(@RequestHeader("Token") String token,
                                          @RequestParam("token_id") long token_id,
                                          @RequestParam("ticker_id") long ticker_id) {

        RegistrationToken registrationToken = tokenRepository.findRegistrationTokenById(token_id);
        if (registrationToken == null || !registrationToken.getToken().equals(token))
            return null;
        registrationToken.setLastVisited(System.currentTimeMillis());
        tokenRepository.save(registrationToken);

        Ticker ticker = tickerRepository.getTickerByTokenIdAndId(token_id, ticker_id);

        CurrencyUpdate currencyUpdate = currencyUpdateRepository
                .findCurrencyUpdateByCryptoIdAndCountryId(ticker.getCryptoId(), ticker.getCountryId());
        if (currencyUpdate != null) ticker.setTicker(currencyUpdate);

        return ticker;
    }


    @RequestMapping(value = "/addTicker", method = RequestMethod.POST)
    public ResponseId addTicker(@RequestBody Ticker ticker) {

        if (ticker == null)
            return new ResponseId("Sent null entity!");

        try {
            ticker.setCreated(System.currentTimeMillis());
            Long id = tickerRepository.save(ticker).getId();
            BrServerApplication.log.info("Saved  " + ticker.toString());

            return new ResponseId(id);
        } catch (Exception e) {
            return new ResponseId("Something gone wrong");
        }
    }


    @RequestMapping(value = "/deleteTicker", method = RequestMethod.GET)
    public ResponseEntity<String> deleteSubscribe(@RequestHeader("Token") String token,
                                                  @RequestParam("id") long id) {
        try {
            Long token_id = tickerRepository.findOne(id).getTokenId();
            RegistrationToken registrationToken = tokenRepository.findRegistrationTokenById(token_id);
            if (registrationToken == null || !registrationToken.getToken().equals(token))
                return new ResponseEntity<>("Invalid token!", HttpStatus.BAD_REQUEST);
            registrationToken.setLastVisited(System.currentTimeMillis());
            tokenRepository.save(registrationToken);

            tickerRepository.delete(id);
            subscribeRepository.delete(subscribeRepository.findSubscribesByTickerId(id));
            BrServerApplication.log.info("Deleted ticker with id=" + id);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Something gone wrong", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/exmo", method = RequestMethod.GET)
    public String getExmoTicker(@RequestParam("pair") String pair) {

        ExmoTicker exmoTicker = restTemplate.getForObject(Config.EXMO_TICKER_URL, ExmoTicker.class);
        BrServerApplication.log.info(exmoTicker.XRPUSD.toString());
        return exmoTicker.XRPUSD.toString();
    }

}
