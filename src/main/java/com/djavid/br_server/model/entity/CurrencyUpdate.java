package com.djavid.br_server.model.entity;

import com.djavid.br_server.model.entity.cryptonator.CoinMarketCapTicker;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class CurrencyUpdate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String cryptoId;
    private String countryId;
    private double price;

    private int rank;
    private double market_cap_usd;
    private double available_supply;
    private double total_supply;
    private double percent_change_1h;
    private double percent_change_24h;
    private double percent_change_7d;
    private long last_updated;


    public CurrencyUpdate() { }

    public void setData(CoinMarketCapTicker ticker) {
        this.cryptoId = ticker.getSymbol();
        this.countryId = ticker.getCountry_symbol();
        this.price = ticker.getPrice();
        this.rank = ticker.getRank();
        this.market_cap_usd = ticker.getMarket_cap_usd();
        this.available_supply = ticker.getAvailable_supply();
        this.total_supply = ticker.getTotal_supply();
        this.percent_change_1h = ticker.getPercent_change_1h();
        this.percent_change_24h = ticker.getPercent_change_24h();
        this.percent_change_7d = ticker.getPercent_change_7d();
        this.last_updated = ticker.getLast_updated();
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

    public int getRank() {
        return rank;
    }
    public void setRank(int rank) {
        this.rank = rank;
    }

    public double getMarket_cap_usd() {
        return market_cap_usd;
    }
    public void setMarket_cap_usd(double market_cap_usd) {
        this.market_cap_usd = market_cap_usd;
    }

    public double getAvailable_supply() {
        return available_supply;
    }
    public void setAvailable_supply(double available_supply) {
        this.available_supply = available_supply;
    }

    public double getTotal_supply() {
        return total_supply;
    }
    public void setTotal_supply(double total_supply) {
        this.total_supply = total_supply;
    }

    public double getPercent_change_1h() {
        return percent_change_1h;
    }
    public void setPercent_change_1h(double percent_change_1h) {
        this.percent_change_1h = percent_change_1h;
    }

    public double getPercent_change_24h() {
        return percent_change_24h;
    }
    public void setPercent_change_24h(double percent_change_24h) {
        this.percent_change_24h = percent_change_24h;
    }

    public double getPercent_change_7d() {
        return percent_change_7d;
    }
    public void setPercent_change_7d(double percent_change_7d) {
        this.percent_change_7d = percent_change_7d;
    }

    public long getLast_updated() {
        return last_updated;
    }
    public void setLast_updated(long last_updated) {
        this.last_updated = last_updated;
    }
}
