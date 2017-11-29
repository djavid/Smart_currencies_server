package com.djavid.br_server.model.entity.cryptonator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class CryptonatorTicker {

    private TickerCryptonator tickerCryptonator;
    private Long timestamp;
    private Boolean success;
    private String error;


    public CryptonatorTicker() { }

    public CryptonatorTicker(TickerCryptonator tickerCryptonator, Long timestamp, Boolean success, String error) {
        super();
        this.tickerCryptonator = tickerCryptonator;
        this.timestamp = timestamp;
        this.success = success;
        this.error = error;
    }

    public TickerCryptonator getTickerCryptonator() {
        return tickerCryptonator;
    }
    public void setTickerCryptonator(TickerCryptonator tickerCryptonator) {
        this.tickerCryptonator = tickerCryptonator;
    }

    public Long getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Boolean getSuccess() {
        return success;
    }
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "CryptonatorTicker{" +
                "tickerCryptonator=" + tickerCryptonator +
                ", timestamp=" + timestamp +
                ", success=" + success +
                ", error='" + error + '\'' +
                '}';
    }
}

