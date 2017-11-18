package com.djavid.br_server.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Subscribe {

    @Id
    @GeneratedValue
    private long id;
    private String currId;
    private String countryId;
    private boolean isTrendingUp;
    private String value;
    private long tokenId;

    @Override
    public String toString() {
        return "Subscribe{" +
                "currId='" + currId + '\'' +
                ", countryId='" + countryId + '\'' +
                ", isTrendingUp=" + isTrendingUp +
                ", value='" + value + '\'' +
                ", tokenId=" + tokenId +
                '}';
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getCurrId() {
        return currId;
    }
    public void setCurrId(String currId) {
        this.currId = currId;
    }

    public String getCountryId() {
        return countryId;
    }
    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

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

    public long getTokenId() {
        return tokenId;
    }
    public void setTokenId(long tokenId) {
        this.tokenId = tokenId;
    }
}
