package com.djavid.br_server.controller;

import com.djavid.br_server.BrServerApplication;
import com.djavid.br_server.model.entity.ResponseId;
import com.djavid.br_server.model.entity.Subscribe;
import com.djavid.br_server.model.repository.SubscribeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class SubscribeController {

    private final SubscribeRepository subscribeRepository;


    public SubscribeController( SubscribeRepository subscribeRepository) {
        this.subscribeRepository = subscribeRepository;
    }


    @RequestMapping(value = "/getSubscribes/", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Subscribe> getSubscribes() {
        return subscribeRepository.findAll();
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


//    @RequestMapping(value = "/deleteSubscribes", method = RequestMethod.GET)
//    public ResponseEntity<String> deleteAllSubscribes() {
//        try {
//            subscribeRepository.deleteAll();
//            BrServerApplication.log.info("Deleted all subscribes");
//            return new ResponseEntity<>(HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>("Something gone wrong", HttpStatus.BAD_REQUEST);
//        }
//    }


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


}
