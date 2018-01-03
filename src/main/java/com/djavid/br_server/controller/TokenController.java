package com.djavid.br_server.controller;

import com.djavid.br_server.BrServerApplication;
import com.djavid.br_server.model.entity.RegistrationToken;
import com.djavid.br_server.model.entity.ResponseId;
import com.djavid.br_server.model.repository.RegistrationTokenRepository;
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


    @RequestMapping(value = "/registerToken", method = RequestMethod.GET)
    public ResponseId registerToken(@RequestParam("token") String device_id, @RequestParam("id") long db_id) {

        if (device_id.equals(""))
            return new ResponseId("Wrong device id!");

        RegistrationToken registrationToken = registrationTokenRepository.findRegistrationTokenByToken(device_id);
        if (registrationToken != null && registrationToken.getToken().equals(device_id))
            return new ResponseId("Device id was already registered!",
                    registrationToken.getId());

        try {

            if (db_id == 0 || registrationTokenRepository.findRegistrationTokenById(db_id) == null) {
                RegistrationToken token = new RegistrationToken(device_id);
                token.setCreated(System.currentTimeMillis());

                Long id = registrationTokenRepository.save(token).getId();
                BrServerApplication.log.info("Saved token(" + device_id + ") with id(" + id + ")");
                return new ResponseId(id);

            } else if (registrationTokenRepository.findRegistrationTokenById(db_id) != null) {

                registrationTokenRepository.findRegistrationTokenById(db_id).setToken(device_id);
                BrServerApplication.log.info("Updated token(" + device_id + ") with id(" + db_id + ")");
                return new ResponseId(db_id);

            }

            return new ResponseId("Something gone wrong");

        } catch (Exception e) {
            return new ResponseId(e.getMessage());
        }
    }

}
