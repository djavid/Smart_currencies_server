package com.djavid.br_server.model.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class RegistrationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    public String token;

    public RegistrationToken(String token) {
        this.id = 0;
        this.token = token;
    }

    public RegistrationToken() { }

}