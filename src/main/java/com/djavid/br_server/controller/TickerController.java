package com.djavid.br_server.controller;

import com.djavid.br_server.BrServerApplication;
import com.djavid.br_server.model.entity.ResponseId;
import com.djavid.br_server.model.entity.Ticker;
import com.djavid.br_server.model.repository.RegistrationTokenRepository;
import com.djavid.br_server.model.repository.SubscribeRepository;
import com.djavid.br_server.model.repository.TickerRepository;
import org.springframework.web.bind.annotation.*;


@RestController
public class TickerController {

    private final TickerRepository tickerRepository;
    private final SubscribeRepository subscribeRepository;
    private final RegistrationTokenRepository tokenRepository;


    public TickerController(TickerRepository tickerRepository, SubscribeRepository subscribeRepository, RegistrationTokenRepository tokenRepository) {
        this.tickerRepository = tickerRepository;
        this.subscribeRepository = subscribeRepository;
        this.tokenRepository = tokenRepository;
    }



    @RequestMapping(value = "/getTickers/", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Ticker> getSubscribes() {
        return tickerRepository.findAll();
    }


    @RequestMapping(value = "/addTicker", method = RequestMethod.POST)
    public ResponseId subscribe(@RequestBody Ticker ticker) {
        if (ticker == null)
            return new ResponseId("Sent null entity!");

        try {
            Long id = tickerRepository.save(ticker).getId();
            BrServerApplication.log.info("Saved " + ticker.toString());

            return new ResponseId(id);
        } catch (Exception e) {
            return new ResponseId("Something gone wrong");
        }
    }


}
