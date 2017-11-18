package com.djavid.br_server.controller;

import java.util.concurrent.CompletableFuture;

import com.djavid.br_server.BrServerApplication;
import com.djavid.br_server.model.entity.RegistrationToken;
import com.djavid.br_server.model.entity.Subscribe;
import com.djavid.br_server.model.entity.ResponseId;
import com.djavid.br_server.model.repository.SubscribeRepository;
import com.djavid.br_server.push.AndroidPushNotificationsService;
import com.djavid.br_server.model.repository.RegistrationTokenRepository;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseId registerToken(@RequestParam("token") String device_id) {

        if (device_id.equals(""))
            return new ResponseId("Wrong device id!");

        if (registrationTokenRepository.findRegistrationTokenByToken(device_id) != null)
            return new ResponseId("Device id was already registered!");

        try {
            Long id = registrationTokenRepository.save(new RegistrationToken(device_id)).id;

//            Long id = registrationTokenRepository.findRegistrationTokenByToken(device_id).id;
            BrServerApplication.log.info("Saved token(" + device_id + ") with id(" + id + ")");

            return new ResponseId(id);
        } catch (Exception e) {
            return new ResponseId("Something gone wrong");
        }
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
    public ResponseEntity<String> deleteSubscribe(@RequestParam("id") long id) {
        try {
            subscribeRepository.delete(id);
            BrServerApplication.log.info("Deleted subscribe with id=" + id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Something gone wrong", HttpStatus.BAD_REQUEST);
        }
    }

}
