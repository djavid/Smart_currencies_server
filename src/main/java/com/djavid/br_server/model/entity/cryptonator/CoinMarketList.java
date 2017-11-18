package com.djavid.br_server.model.entity.cryptonator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinMarketList {

    @JsonProperty
    private List<CoinMarketCapTicker> tickers;


    public CoinMarketList() { }

    public CoinMarketList(List<CoinMarketCapTicker> tickers) {
        this.tickers = tickers;
    }

    public List<CoinMarketCapTicker> getTickers() {
        return tickers;
    }
    public void setTickers(List<CoinMarketCapTicker> tickers) {
        this.tickers = tickers;
    }
}
