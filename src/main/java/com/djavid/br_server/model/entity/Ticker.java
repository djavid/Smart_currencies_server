package com.djavid.br_server.model.entity;

import javax.persistence.*;


@Entity
public class Ticker {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long tokenId;
    private String cryptoId;
    private String countryId;
    @ManyToOne
    @JoinColumn(name = "cu_id")
    private CurrencyUpdate ticker;

    private long created;
    private long lastVisited;


    @Override
    public String toString() {
        return "Ticker{" +
                "id=" + id +
                ", tokenId=" + tokenId +
                ", cryptoId='" + cryptoId + '\'' +
                ", countryId='" + countryId + '\'' +
                ", ticker=" + ticker +
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

    public CurrencyUpdate getTicker() {
        return ticker;
    }
    public void setTicker(CurrencyUpdate ticker) {
        this.ticker = ticker;
    }

    public long getCreated() {
        return created;
    }
    public void setCreated(long created) {
        this.created = created;
    }

    public long getLastVisited() {
        return lastVisited;
    }
    public void setLastVisited(long lastVisited) {
        this.lastVisited = lastVisited;
    }
}
