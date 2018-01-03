package com.djavid.br_server.model.entity;


public class ResponseTickers {

    public String error;
    public Iterable<Ticker> tickers;


    public ResponseTickers(String error) {
        this.error = error;
    }

    public ResponseTickers(String error, Iterable<Ticker> tickers) {
        this.error = error;
        this.tickers = tickers;
    }

    public ResponseTickers(Iterable<Ticker> tickers) {
        this.tickers = tickers;
        error = "";
    }

}
