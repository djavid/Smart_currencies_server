package com.djavid.br_server.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class RegistrationToken {

    @Id
    @GeneratedValue
    public long id;

    public String token;

    public RegistrationToken(String token) {
        this.token = token;
    }

    public RegistrationToken() { }
}