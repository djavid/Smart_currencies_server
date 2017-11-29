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
    private long tickerId;
    private String currId;
    private String countryId;


    @Override
    public String toString() {
        return "Subscribe{" +
                "id=" + id +
                ", isTrendingUp=" + isTrendingUp +
                ", value='" + value + '\'' +
                ", tickerId=" + tickerId +
                ", currId='" + currId + '\'' +
                ", countryId='" + countryId + '\'' +
                '}';
    }


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

    public long getTickerId() {
        return tickerId;
    }
    public void setTickerId(long tickerId) {
        this.tickerId = tickerId;
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
}
