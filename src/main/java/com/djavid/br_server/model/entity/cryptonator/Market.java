package com.djavid.br_server.model.entity.cryptonator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Market {

    private String market;
    private Double price;
    private Double volume;


    public Market() { }

    public Market(String market, Double price, Double volume) {
        super();
        this.market = market;
        this.price = price;
        this.volume = volume;
    }

    public String getMarket() {
        return market;
    }
    public void setMarket(String market) {
        this.market = market;
    }

    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getVolume() {
        return volume;
    }
    public void setVolume(Double volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "Market{" +
                "market='" + market + '\'' +
                ", price=" + price +
                ", volume=" + volume +
                '}';
    }
}
