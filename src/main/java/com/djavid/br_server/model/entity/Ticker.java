package com.djavid.br_server.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;


@Entity
public class Ticker {

    @Id
    @GeneratedValue
    private long id;
    private long tokenId;
    private String cryptoId;
    private String countryId;


    @Override
    public String toString() {
        return "Ticker{" +
                "id=" + id +
                ", tokenId=" + tokenId +
                ", cryptoId='" + cryptoId + '\'' +
                ", countryId='" + countryId + '\'' +
                '}';
    }


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public long getTokenId() {
        return tokenId;
    }
    public void setTokenId(long tokenId) {
        this.tokenId = tokenId;
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

}
