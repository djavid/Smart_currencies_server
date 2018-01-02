package com.djavid.br_server.model.entity.exmo;


public class ExmoPair {

    private String buyPrice;
    private String sellPrice;
    private String lastTrade;
    private String high;
    private String low;
    private String avg;
    private String vol;
    private String volCurr;
    private Integer updated;


    @Override
    public String toString() {
        return "ExmoPair{" +
                "buyPrice='" + buyPrice + '\'' +
                ", sellPrice='" + sellPrice + '\'' +
                ", lastTrade='" + lastTrade + '\'' +
                ", high='" + high + '\'' +
                ", low='" + low + '\'' +
                ", avg='" + avg + '\'' +
                ", vol='" + vol + '\'' +
                ", volCurr='" + volCurr + '\'' +
                ", updated=" + updated +
                '}';
    }


    public String getBuyPrice() {
        return buyPrice;
    }
    public void setBuyPrice(String buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getSellPrice() {
        return sellPrice;
    }
    public void setSellPrice(String sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getLastTrade() {
        return lastTrade;
    }
    public void setLastTrade(String lastTrade) {
        this.lastTrade = lastTrade;
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

    public String getVolCurr() {
        return volCurr;
    }
    public void setVolCurr(String volCurr) {
        this.volCurr = volCurr;
    }

    public Integer getUpdated() {
        return updated;
    }
    public void setUpdated(Integer updated) {
        this.updated = updated;
    }
}
