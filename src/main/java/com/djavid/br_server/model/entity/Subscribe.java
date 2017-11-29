package com.djavid.br_server.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Subscribe {

    @Id
    @GeneratedValue
    private long id;
    private boolean isTrendingUp;
    private String value;
    private Ticker ticker;

    //private String currId;
    //private String countryId;
    //private RegistrationToken tokenId;


    @Override
    public String toString() {
        return "Subscribe{" +
                "id=" + id +
                ", isTrendingUp=" + isTrendingUp +
                ", value='" + value + '\'' +
                ", ticker=" + ticker +
                '}';
    }

    //    public String getCurrId() {
//        return currId;
//    }
//    public void setCurrId(String currId) {
//        this.currId = currId;
//    }
//
//    public String getCountryId() {
//        return countryId;
//    }
//    public void setCountryId(String countryId) {
//        this.countryId = countryId;
//    }

//    public RegistrationToken getTokenId() {
//        return tokenId;
//    }
//    public void setTokenId(RegistrationToken tokenId) {
//        this.tokenId = tokenId;
//    }


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty(value="isTrendingUp")
    public boolean isTrendingUp() {
        return isTrendingUp;
    }
    public void setTrendingUp(boolean trendingUp) {
        this.isTrendingUp = trendingUp;
    }

    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    public Ticker getTicker() {
        return ticker;
    }
    public void setTicker(Ticker ticker) {
        this.ticker = ticker;
    }

}
