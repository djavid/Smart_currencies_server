package com.djavid.br_server.controller;

import com.djavid.br_server.model.entity.RegistrationToken;
import com.djavid.br_server.model.repository.RegistrationTokenRepository;
import com.djavid.br_server.push.AndroidPushNotificationsService;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;


@RestController
public class PushController {

    private AndroidPushNotificationsService androidPushNotificationsService;
    private final RegistrationTokenRepository registrationTokenRepository;


    public PushController(RegistrationTokenRepository registrationTokenRepository,
                          AndroidPushNotificationsService androidPushNotificationsService) {
        this.registrationTokenRepository = registrationTokenRepository;
        this.androidPushNotificationsService = androidPushNotificationsService;
    }


    @RequestMapping(value = "/send", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> send(@RequestParam("title") String title, @RequestParam("body") String desc) throws JSONException {

        RegistrationToken token = registrationTokenRepository.findAll().iterator().next();

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

}
