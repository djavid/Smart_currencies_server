package com.djavid.br_server.controller;

import com.djavid.br_server.BrServerApplication;
import com.djavid.br_server.model.entity.RegistrationToken;
import com.djavid.br_server.model.entity.ResponseId;
import com.djavid.br_server.model.repository.RegistrationTokenRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class TokenController {

    private final RegistrationTokenRepository registrationTokenRepository;


    public TokenController(RegistrationTokenRepository registrationTokenRepository) {
        this.registrationTokenRepository = registrationTokenRepository;
    }


    @RequestMapping(value = "/getTokens/", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<RegistrationToken> getTokens() {
        return registrationTokenRepository.findAll();
    }


    @RequestMapping(value = "/getTokens/{id}", method = RequestMethod.GET)
    @ResponseBody
    public RegistrationToken getToken(@PathVariable("id") long id) {
        return registrationTokenRepository.findOne(id);
    }


    @RequestMapping(value = "/deleteToken", method = RequestMethod.GET)
    public ResponseEntity<String> deleteToken(@RequestParam("id") long id) {
        try {
            registrationTokenRepository.delete(id);
            BrServerApplication.log.info("Deleted token with id=" + id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Something gone wrong", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/deleteTokens", method = RequestMethod.GET)
    public ResponseEntity<String> deleteAllTokens() {
        try {
            registrationTokenRepository.deleteAll();
            BrServerApplication.log.info("Deleted all tokens");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Something gone wrong", HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = "/registerToken", method = RequestMethod.GET)
    public ResponseId registerToken(@RequestParam("token") String device_id, @RequestParam("id") long db_id) {

        if (device_id.equals(""))
            return new ResponseId("Wrong device id!");

        if (registrationTokenRepository.findRegistrationTokenByToken(device_id) != null)
            return new ResponseId("Device id was already registered!",
                    registrationTokenRepository.findRegistrationTokenByToken(device_id).id);

        try {
            if (db_id == 0) {

                Long id = registrationTokenRepository.save(new RegistrationToken(device_id)).id;
                BrServerApplication.log.info("Saved token(" + device_id + ") with id(" + id + ")");
                return new ResponseId(id);

            } else if (registrationTokenRepository.findRegistrationTokenById(db_id) != null) {

                registrationTokenRepository.findRegistrationTokenById(db_id).token = device_id;
                BrServerApplication.log.info("Updated token(" + device_id + ") with id(" + db_id + ")");
                return new ResponseId(db_id);

            }

            return new ResponseId("Something gone wrong");

        } catch (Exception e) {
            return new ResponseId("Something gone wrong");
        }
    }

}
