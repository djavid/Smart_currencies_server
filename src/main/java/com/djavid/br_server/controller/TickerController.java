package com.djavid.br_server.controller;

import com.djavid.br_server.BrServerApplication;
import com.djavid.br_server.model.entity.ResponseId;
import com.djavid.br_server.model.entity.Ticker;
import com.djavid.br_server.model.repository.RegistrationTokenRepository;
import com.djavid.br_server.model.repository.SubscribeRepository;
import com.djavid.br_server.model.repository.TickerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class TickerController {

    private final TickerRepository tickerRepository;
    private final SubscribeRepository subscribeRepository;
    private final RegistrationTokenRepository tokenRepository;


    public TickerController(TickerRepository tickerRepository, SubscribeRepository subscribeRepository,
                            RegistrationTokenRepository tokenRepository) {
        this.tickerRepository = tickerRepository;
        this.subscribeRepository = subscribeRepository;
        this.tokenRepository = tokenRepository;
    }


    @RequestMapping(value = "/getTickers/", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Ticker> getAllTickers() {
        return tickerRepository.findAll();
    }


    @RequestMapping(value = "/getTickers", method = RequestMethod.GET)
    public Iterable<Ticker> getTickersByTokenId(@RequestParam("token_id") long token_id) {
        return tickerRepository.getTickersByTokenId(token_id);
    }


    @RequestMapping(value = "/addTicker", method = RequestMethod.POST)
    public ResponseId subscribe(@RequestBody Ticker ticker) {
        if (ticker == null)
            return new ResponseId("Sent null entity!");

        try {
            Long id = tickerRepository.save(ticker).getId();
            BrServerApplication.log.info("Saved  " + ticker.toString());

            return new ResponseId(id);
        } catch (Exception e) {
            return new ResponseId("Something gone wrong");
        }
    }


    @RequestMapping(value = "/deleteTicker", method = RequestMethod.GET)
    public ResponseEntity<String> deleteSubscribe(@RequestParam("id") long id) {
        try {
            tickerRepository.delete(id);
            subscribeRepository.delete(subscribeRepository.findSubscribesByTickerId(id));
            BrServerApplication.log.info("Deleted ticker with id=" + id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Something gone wrong", HttpStatus.BAD_REQUEST);
        }
    }

}
