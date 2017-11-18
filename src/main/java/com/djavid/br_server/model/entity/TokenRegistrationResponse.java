package com.djavid.br_server.model.entity;


public class TokenRegistrationResponse {

    public String error;
    public long id;


    public TokenRegistrationResponse(long id) {
        this.id = id;
        error = "";
    }

    public TokenRegistrationResponse(String error) {
        this.error = error;
    }
}
