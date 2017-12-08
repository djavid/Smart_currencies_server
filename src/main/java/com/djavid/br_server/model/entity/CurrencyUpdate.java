package com.djavid.br_server.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class CurrencyUpdate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String cryptoId;
    private String countryId;
    private double price;


    public CurrencyUpdate(String cryptoId, String countryId, double price) {
        this.cryptoId = cryptoId;
        this.countryId = countryId;
        this.price = price;
    }


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getCryptoId() {
        return cryptoId;
    }
    public void setCryptoId(String cryptoId) {
        this.cryptoId = cryptoId;
    }

    public String getCountryId() {
        return countryId;
    }
    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}
