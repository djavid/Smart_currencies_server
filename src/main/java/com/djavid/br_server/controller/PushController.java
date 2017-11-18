package com.djavid.br_server.controller;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

import com.djavid.br_server.model.entity.RegistrationToken;
import com.djavid.br_server.model.entity.Subscribe;
import com.djavid.br_server.model.entity.TokenRegistrationResponse;
import com.djavid.br_server.model.repository.SubscribeRepository;
import com.djavid.br_server.push.AndroidPushNotificationsService;
import com.djavid.br_server.model.repository.RegistrationTokenRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;


@RestController
public class PushController {

    @Autowired
    private AndroidPushNotificationsService androidPushNotificationsService;

    private final RegistrationTokenRepository registrationTokenRepository;
    private final SubscribeRepository subscribeRepository;


    public PushController(RegistrationTokenRepository registrationTokenRepository,
                          SubscribeRepository subscribeRepository,
                          AndroidPushNotificationsService androidPushNotificationsService) {
        this.registrationTokenRepository = registrationTokenRepository;
        this.androidPushNotificationsService = androidPushNotificationsService;
        this.subscribeRepository = subscribeRepository;
    }

    @RequestMapping(value = "/getTokens/", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<RegistrationToken> getTokens() {
        return registrationTokenRepository.findAll();
    }

    @RequestMapping(value = "/getSubscribes/", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Subscribe> getSubscribes() {
        return subscribeRepository.findAll();
    }

    @RequestMapping(value = "/getTokens/{id}", method = RequestMethod.GET)
    @ResponseBody
    public RegistrationToken getToken(@PathVariable("id") long id) {
        return registrationTokenRepository.findOne(id);
    }

    @RequestMapping(value = "/send", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> send(@RequestParam("title") String title, @RequestParam("body") String desc) throws JSONException {

        RegistrationToken token = registrationTokenRepository.findOne(1L);

        try {
            CompletableFuture<String> pushNotification = androidPushNotificationsService.send(token.token, title, desc);
            CompletableFuture.allOf(pushNotification).join();
            String firebaseResponse = pushNotification.get();

            return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>("Push Notification ERROR!", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public TokenRegistrationResponse registerToken(@RequestParam("token") String device_id) {

        if (device_id.equals(""))
            return new TokenRegistrationResponse("Wrong device id!");

        if (registrationTokenRepository.findRegistrationTokenByToken(device_id) != null)
            return new TokenRegistrationResponse("Device id was already registered!");

        try {
            registrationTokenRepository.save(new RegistrationToken(device_id));

            Long id = registrationTokenRepository.findRegistrationTokenByToken(device_id).id;
            System.out.println("Saved token(" + device_id + ") with id(" + id + ")");

            return new TokenRegistrationResponse(id);
        } catch (Exception e) {
            return new TokenRegistrationResponse("Something gone wrong");
        }
    }

    @RequestMapping(value = "/subscribe", method = RequestMethod.POST)
    public ResponseEntity<String> subscribe(@RequestBody Subscribe subscribe) {
        if (subscribe == null)
            return new ResponseEntity<>("Sent null entity!", HttpStatus.BAD_REQUEST);

        try {
            subscribeRepository.save(subscribe);
            System.out.println("Saved " + subscribe.toString());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Something gone wrong", HttpStatus.BAD_REQUEST);
        }
    }

}
