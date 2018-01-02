package com.djavid.br_server.model.entity.exmo;


public class ExmoPair {

    private String buy_price;
    private String sell_price;
    private String last_trade;
    private String high;
    private String low;
    private String avg;
    private String vol;
    private String vol_curr;
    private Integer updated;


    @Override
    public String toString() {
        return "ExmoPair{" +
                "buy_price='" + buy_price + '\'' +
                ", sell_price='" + sell_price + '\'' +
                ", last_trade='" + last_trade + '\'' +
                ", high='" + high + '\'' +
                ", low='" + low + '\'' +
                ", avg='" + avg + '\'' +
                ", vol='" + vol + '\'' +
                ", vol_curr='" + vol_curr + '\'' +
                ", updated=" + updated +
                '}';
    }


    public String getBuy_price() {
        return buy_price;
    }
    public void setBuy_price(String buy_price) {
        this.buy_price = buy_price;
    }

    public String getSell_price() {
        return sell_price;
    }
    public void setSell_price(String sell_price) {
        this.sell_price = sell_price;
    }

    public String getLast_trade() {
        return last_trade;
    }
    public void setLast_trade(String last_trade) {
        this.last_trade = last_trade;
    }

    public String getHigh() {
        return high;
    }
    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }
    public void setLow(String low) {
        this.low = low;
    }

    public String getAvg() {
        return avg;
    }
    public void setAvg(String avg) {
        this.avg = avg;
    }

    public String getVol() {
        return vol;
    }
    public void setVol(String vol) {
        this.vol = vol;
    }

    public String getVol_curr() {
        return vol_curr;
    }
    public void setVol_curr(String vol_curr) {
        this.vol_curr = vol_curr;
    }

    public Integer getUpdated() {
        return updated;
    }
    public void setUpdated(Integer updated) {
        this.updated = updated;
    }
}
