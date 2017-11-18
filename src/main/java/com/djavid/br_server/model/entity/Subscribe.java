package com.djavid.br_server.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Subscribe {

    @Id
    @GeneratedValue
    long id;

    String curr_id;

    String country_id;

    boolean is_trending_up;

    String value;

    long token_id;

    @Override
    public String toString() {
        return "Subscribe{" +
                "curr_id='" + curr_id + '\'' +
                ", country_id='" + country_id + '\'' +
                ", is_trending_up=" + is_trending_up +
                ", value='" + value + '\'' +
                ", token_id=" + token_id +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCurr_id() {
        return curr_id;
    }

    public void setCurr_id(String curr_id) {
        this.curr_id = curr_id;
    }

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    public boolean isIs_trending_up() {
        return is_trending_up;
    }

    public void setIs_trending_up(boolean is_trending_up) {
        this.is_trending_up = is_trending_up;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getToken_id() {
        return token_id;
    }

    public void setToken_id(long token_id) {
        this.token_id = token_id;
    }
}
