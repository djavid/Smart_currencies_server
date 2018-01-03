package com.djavid.br_server.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class RegistrationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    public String token;
    public long created;
    public long lastVisited;

    public RegistrationToken(String token) {
        this.id = 0;
        this.token = token;
        created = System.currentTimeMillis();
        lastVisited = created;
    }

    public RegistrationToken() { }

}