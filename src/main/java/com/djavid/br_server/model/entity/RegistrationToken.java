package com.djavid.br_server.model.entity;

import org.hibernate.annotations.GenericGenerator;

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

    public RegistrationToken(String token) {
        this.token = token;
    }

    public RegistrationToken() { }

}