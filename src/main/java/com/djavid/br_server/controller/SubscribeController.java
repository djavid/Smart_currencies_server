package com.djavid.br_server.controller;

import com.djavid.br_server.BrServerApplication;
import com.djavid.br_server.model.entity.RegistrationToken;
import com.djavid.br_server.model.entity.ResponseId;
import com.djavid.br_server.model.entity.Subscribe;
import com.djavid.br_server.model.repository.RegistrationTokenRepository;
import com.djavid.br_server.model.repository.SubscribeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class SubscribeController {

    private final SubscribeRepository subscribeRepository;
    private final RegistrationTokenRepository tokenRepository;


    public SubscribeController(SubscribeRepository subscribeRepository, RegistrationTokenRepository tokenRepository) {
        this.subscribeRepository = subscribeRepository;
        this.tokenRepository = tokenRepository;
    }


    @RequestMapping(value = "/getSubscribes/", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Subscribe> getSubscribes() {
        return subscribeRepository.findAll();
    }


    @RequestMapping(value = "/getSubscribes", method = RequestMethod.GET)
    public Iterable<Subscribe> getSubscribesByTokenId(@RequestHeader("Token") String token,
                                                      @RequestParam("token_id") Long token_id) {

        RegistrationToken registrationToken = tokenRepository.findRegistrationTokenById(token_id);
        if (registrationToken == null || !registrationToken.getToken().equals(token))
            return null;
        registrationToken.setLastVisited(System.currentTimeMillis());
        tokenRepository.save(registrationToken);

        return subscribeRepository.findSubscribesByTokenId(token_id);
    }


    @RequestMapping(value = "/subscribe", method = RequestMethod.POST)
    public ResponseId subscribe(@RequestBody Subscribe subscribe) {
        if (subscribe == null)
            return new ResponseId("Sent null entity!");

        try {
            Long id = subscribeRepository.save(subscribe).getId();
            BrServerApplication.log.info("Saved " + subscribe.toString());

            return new ResponseId(id);

        } catch (Exception e) {
            return new ResponseId("Something gone wrong");
        }
    }


    @RequestMapping(value = "/deleteSubscribe", method = RequestMethod.GET)
    public ResponseEntity<String> deleteSubscribe(@RequestHeader("Token") String token,
                                                  @RequestParam("id") long id) {
        try {

            Long token_id = subscribeRepository.findOne(id).getTokenId();
            RegistrationToken registrationToken = tokenRepository.findRegistrationTokenById(token_id);
            if (registrationToken == null || !registrationToken.getToken().equals(token))
                return new ResponseEntity<>("Invalid token!", HttpStatus.BAD_REQUEST);
            registrationToken.setLastVisited(System.currentTimeMillis());
            tokenRepository.save(registrationToken);

            subscribeRepository.delete(id);
            BrServerApplication.log.info("Deleted subscribe with id=" + id);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Something gone wrong", HttpStatus.BAD_REQUEST);
        }
    }

}
