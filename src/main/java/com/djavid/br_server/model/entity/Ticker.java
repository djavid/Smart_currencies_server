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
    private RegistrationToken tokenId;
    private String currId;
    private String countryId;
    private List<Subscribe> subscribesList;


    @Override
    public String toString() {
        return "Ticker{" +
                "id=" + id +
                ", tokenId=" + tokenId +
                ", currId='" + currId + '\'' +
                ", countryId='" + countryId + '\'' +
                '}';
    }


    public RegistrationToken getTokenId() {
        return tokenId;
    }
    public void setTokenId(RegistrationToken tokenId) {
        this.tokenId = tokenId;
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

    public List<Subscribe> getSubscribesList() {
        return subscribesList;
    }
    public void setSubscribesList(List<Subscribe> subscribesList) {
        this.subscribesList = subscribesList;
    }
}
