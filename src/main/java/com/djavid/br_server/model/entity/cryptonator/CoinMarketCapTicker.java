package com.djavid.br_server.model.entity.cryptonator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinMarketCapTicker {

    private String id;
    private String name;
    private String symbol;
    private int rank;
    private double market_cap_usd;
    private double available_supply;
    private double total_supply;
    private double percent_change_1h;
    private double percent_change_24h;
    private double percent_change_7d;
    private long last_updated;

    private double price_btc;
    private double price_usd;
    private double price_eur;
    private double price_cad;
    private double price_cny;
    private double price_jpy;
    private double price_pln;
    private double price_gbp;
    private double price_rub;
    private double price_uah;

    private String country_symbol;


    private Double getPrice(String id) {
        switch (id) {
            case "USD":
                return getPrice_usd();
            case "EUR":
                return getPrice_eur();
            case "CAD":
                return getPrice_cad();
            case "CNY":
                return getPrice_cny();
            case "JPY":
                return getPrice_jpy();
            case "PLN":
                return getPrice_pln();
            case "GBP":
                return getPrice_gbp();
            case "RUB":
                return getPrice_rub();
            case "UAH":
                return getPrice_uah();
            default:
                return 0d;
        }
    }

    public Double getPrice() {
        return getPrice(country_symbol);
    }


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getRank() {
        return rank;
    }
    public void setRank(int rank) {
        this.rank = rank;
    }

    public double getPrice_btc() {
        return price_btc;
    }
    public void setPrice_btc(double price_btc) {
        this.price_btc = price_btc;
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

    private double getPrice_usd() {
        return price_usd;
    }
    private double getPrice_eur() {
        return price_eur;
    }
    private double getPrice_cad() {
        return price_cad;
    }
    private double getPrice_cny() {
        return price_cny;
    }
    private double getPrice_jpy() {
        return price_jpy;
    }
    private double getPrice_pln() {
        return price_pln;
    }
    private double getPrice_gbp() {
        return price_gbp;
    }
    private double getPrice_rub() {
        return price_rub;
    }
    private double getPrice_uah() {
        return price_uah;
    }

    public String getCountry_symbol() {
        return country_symbol;
    }
    public void setCountry_symbol(String country_symbol) {
        this.country_symbol = country_symbol;
    }
}