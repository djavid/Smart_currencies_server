package com.djavid.br_server.model.entity.exmo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "BTC_USD",
    "BTC_EUR",
    "BTC_RUB",
    "BTC_UAH",
    "BTC_PLN",
    "BCH_BTC",
    "BCH_USD",
    "BCH_RUB",
    "BCH_ETH",
    "DASH_BTC",
    "DASH_USD",
    "DASH_RUB",
    "ETH_BTC",
    "ETH_LTC",
    "ETH_USD",
    "ETH_EUR",
    "ETH_RUB",
    "ETH_UAH",
    "ETH_PLN",
    "ETC_BTC",
    "ETC_USD",
    "ETC_RUB",
    "LTC_BTC",
    "LTC_USD",
    "LTC_EUR",
    "LTC_RUB",
    "ZEC_BTC",
    "ZEC_USD",
    "ZEC_EUR",
    "ZEC_RUB",
    "XRP_BTC",
    "XRP_USD",
    "XRP_RUB",
    "XMR_BTC",
    "XMR_USD",
    "XMR_EUR",
    "BTC_USDT",
    "ETH_USDT",
    "USDT_USD",
    "USDT_RUB",
    "USD_RUB",
    "DOGE_BTC",
    "WAVES_BTC",
    "WAVES_RUB",
    "KICK_BTC",
    "KICK_ETH"
})
public class ExmoTicker {

    @JsonProperty("BTC_USD")
    public ExmoPair BTCUSD;
    @JsonProperty("BTC_EUR")
    public ExmoPair BTCEUR;
    @JsonProperty("BTC_RUB")
    public ExmoPair BTCRUB;
    @JsonProperty("BTC_UAH")
    public ExmoPair BTCUAH;
    @JsonProperty("BTC_PLN")
    public ExmoPair BTCPLN;
    @JsonProperty("BCH_BTC")
    public ExmoPair BCHBTC;
    @JsonProperty("BCH_USD")
    public ExmoPair BCHUSD;
    @JsonProperty("BCH_RUB")
    public ExmoPair BCHRUB;
    @JsonProperty("BCH_ETH")
    public ExmoPair BCHETH;
    @JsonProperty("DASH_BTC")
    public ExmoPair DASHBTC;
    @JsonProperty("DASH_USD")
    public ExmoPair DASHUSD;
    @JsonProperty("DASH_RUB")
    public ExmoPair DASHRUB;
    @JsonProperty("ETH_BTC")
    public ExmoPair ETHBTC;
    @JsonProperty("ETH_LTC")
    public ExmoPair ETHLTC;
    @JsonProperty("ETH_USD")
    public ExmoPair ETHUSD;
    @JsonProperty("ETH_EUR")
    public ExmoPair ETHEUR;
    @JsonProperty("ETH_RUB")
    public ExmoPair ETHRUB;
    @JsonProperty("ETH_UAH")
    public ExmoPair ETHUAH;
    @JsonProperty("ETH_PLN")
    public ExmoPair ETHPLN;
    @JsonProperty("ETC_BTC")
    public ExmoPair ETCBTC;
    @JsonProperty("ETC_USD")
    public ExmoPair ETCUSD;
    @JsonProperty("ETC_RUB")
    public ExmoPair ETCRUB;
    @JsonProperty("LTC_BTC")
    public ExmoPair LTCBTC;
    @JsonProperty("LTC_USD")
    public ExmoPair LTCUSD;
    @JsonProperty("LTC_EUR")
    public ExmoPair LTCEUR;
    @JsonProperty("LTC_RUB")
    public ExmoPair LTCRUB;
    @JsonProperty("ZEC_BTC")
    public ExmoPair ZECBTC;
    @JsonProperty("ZEC_USD")
    public ExmoPair ZECUSD;
    @JsonProperty("ZEC_EUR")
    public ExmoPair ZECEUR;
    @JsonProperty("ZEC_RUB")
    public ExmoPair ZECRUB;
    @JsonProperty("XRP_BTC")
    public ExmoPair XRPBTC;
    @JsonProperty("XRP_USD")
    public ExmoPair XRPUSD;
    @JsonProperty("XRP_RUB")
    public ExmoPair XRPRUB;
    @JsonProperty("XMR_BTC")
    public ExmoPair XMRBTC;
    @JsonProperty("XMR_USD")
    public ExmoPair XMRUSD;
    @JsonProperty("XMR_EUR")
    public ExmoPair XMREUR;
    @JsonProperty("BTC_USDT")
    public ExmoPair BTCUSDT;
    @JsonProperty("ETH_USDT")
    public ExmoPair ETHUSDT;
    @JsonProperty("USDT_USD")
    public ExmoPair USDTUSD;
    @JsonProperty("USDT_RUB")
    public ExmoPair USDTRUB;
    @JsonProperty("USD_RUB")
    public ExmoPair USDRUB;
    @JsonProperty("DOGE_BTC")
    public ExmoPair DOGEBTC;
    @JsonProperty("WAVES_BTC")
    public ExmoPair WAVESBTC;
    @JsonProperty("WAVES_RUB")
    public ExmoPair WAVESRUB;
    @JsonProperty("KICK_BTC")
    public ExmoPair KICKBTC;
    @JsonProperty("KICK_ETH")
    public ExmoPair KICKETH;


}
