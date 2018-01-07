package com.djavid.br_server;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Config {

    public final static long FIXED_DELAY = 30000; //delay of currencies rate updates

    //API
    public final static String COINMARKETCAP_URL = "https://api.coinmarketcap.com/v1/ticker/?convert=";
    public final static String EXMO_TICKER_URL = "https://api.exmo.com/v1/ticker/";

    //CODES
    public static String[] country_codes = new String[]{
            "AED",	"AFN",	"ALL",	"AMD",	"ANG",	"AOA",	"ARS",	"AUD",	"AWG",	"AZN",
            "BAM",	"BBD",	"BDT",	"BGN",	"BHD",	"BIF",	"BMD",	"BND",	"BOB",	"BRL",
            "BSD",	"BTN",	"BWP",	"BYN",	"BZD",	"CAD",	"CDF",	"CHF",	"CLP",	"CNY",
            "COP",	"CRC",	"CUC",	"CUP",	"CVE",	"CZK",	"DJF",	"DKK",	"DOP",	"DZD",
            "EGP",	"ERN",	"ETB",	"EUR",	"FJD",	"FKP",	"GBP",	"GEL",	"GGP",	"GHS",
            "GIP",	"GMD",	"GNF",	"GTQ",	"GYD",	"HKD",	"HNL",	"HRK",	"HTG",	"HUF",
            "IDR",	"ILS",	"IMP",	"INR",	"IQD",	"IRR",	"ISK",	"JEP",	"JMD",	"JOD",
            "JPY",	"KES",	"KGS",	"KHR",	"KMF",	"KPW",	"KRW",	"KWD",	"KYD",	"KZT",
            "LAK",	"LBP",	"LKR",	"LRD",	"LSL",	"LYD",	"MAD",	"MDL",	"MGA",	"MKD",
            "MMK",	"MNT",	"MOP",	"MRO",	"MUR",	"MVR",	"MWK",	"MXN",	"MYR",	"MZN",
            "NAD",	"NGN",	"NIO",	"NOK",	"NPR",	"NZD",	"OMR",	"PAB",	"PEN",	"PGK",
            "PHP",	"PKR",	"PLN",	"PYG",	"QAR",	"RON",	"RSD",	"RUB",	"RWF",	"SAR",
            "SBD",	"SCR",	"SDG",	"SEK",	"SGD",	"SHP",	"SLL",	"SOS",	"SPL*",	"SRD",
            "STD",	"SVC",	"SYP",	"SZL",	"THB",	"TJS",	"TMT",	"TND",	"TOP",	"TRY",
            "TTD",	"TVD",	"TWD",	"TZS",	"UAH",	"UGX",	"USD",	"UYU",	"UZS",	"VEF",
            "VND",	"VUV",	"WST",	"XAF",	"XCD",	"XDR",	"XOF",	"XPF",	"YER",	"ZAR",
            "ZMW",	"ZWD"
    };

    public static Coin[] crypto_coins = {
            new Coin("BTC", "bitcoin"), new Coin("BCH", "bitcoin-cash"), new Coin("XRP", "ripple"),
            new Coin("LTC", "litecoin"), new Coin("ETH", "ethereum"), new Coin("NVC", "novacoin"),
            new Coin("NMC", "namecoin"), new Coin("PPC", "peercoin"), new Coin("DOGE", "dogecoin"),
            new Coin("DASH", "dash"), new Coin("XEM", "nem"), new Coin("XMR", "monero"),
            new Coin("BTG", "bitcoin-gold"), new Coin("EOS", "eos"), new Coin("NEO", "neo"),
            new Coin("ETC", "ethereum-classic"), new Coin("ZEC", "zcash"), new Coin("WAVES", "waves"),
            new Coin("USDT", "tether"), new Coin("NXT", "nxt"), new Coin("XVG", "verge"),
            new Coin("STEEM", "steem"), new Coin("XLM", "stellar"), new Coin("BCN", "bytecoin-bcn"),
            new Coin("STRAT", "stratis"), new Coin("TRX", "tron"), new Coin("ADA", "cardano"),
            new Coin("MIOTA", "iota"), new Coin("BTS", "bitshares"), new Coin("ARDR", "ardor"),
            new Coin("MTH", "monetha")
    };

    public static String[] crypto_coins_array = Stream
            .of(crypto_coins)
            .map(coin -> coin.symbol)
            .sorted()
            .collect(Collectors.toList())
            .toArray(new String[crypto_coins.length]);

    public static String[] country_coins = {"USD", "EUR", "CAD", "CNY", "JPY", "PLN", "GBP", "RUB", "UAH"};


    public static class Coin {
        public String symbol;
        public String id;

        Coin(String symbol, String id) {
            this.symbol = symbol;
            this.id = id;
        }
    }

    public static String convertPrice(double price) {
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
        symbols.setGroupingSeparator(' ');

        DecimalFormat formatter = new DecimalFormat(getPattern(price), symbols);

        return formatter.format(price);
    }

    private static String getPattern(Double price) {

        if (price < 1) {
            return "###,###.######";
        } else
        if (price >= 1 && price < 10) {
            return "###,###.###";
        } else
        if (price >= 10 && price < 100) {
            return "###,###.##";
        } else
        if (price >= 100 && price < 1000) {
            return "###,###.##";
        } else
        if (price >= 1000 && price < 10000) {
            return "###,###.##";
        } else
        if (price >= 10000 && price < 100000) {
            return "###,###.#";
        } else { //if (price > 100000)
            return "###,###";
        }
    }

    public static String getCurrencyFullName(String code) {
        switch (code) {
            case "RUB":
                return "Ruble;Рубль";
            case "USD":
                return "Dollar;Доллар";
            case "EUR":
                return "Euro;Евро";
            case "ISK":
                return ";";
            case "HKD":
                return ";";
            case "TWD":
                return ";";
            case "CHF":
                return ";";
            case "DKK":
                return ";";
            case "CLP":
                return ";";
            case "CAD":
                return ";";
            case "INR":
                return ";";
            case "CNY":
                return ";";
            case "THB":
                return ";";
            case "AUD":
                return ";";
            case "SGD":
                return ";";
            case "KRW":
                return ";";
            case "JPY":
                return ";";
            case "PLN":
                return ";";
            case "GBP":
                return ";";
            case "SEK":
                return ";";
            case "NZD":
                return ";";
            case "BRL":
                return ";";
            case "BTC":
                return "Bitcoin";
            case "BCH":
                return "Bitcoin Cash";
            case "LTC":
                return "Litecoin";
            case "NMC":
                return "Namecoin";
            case "DOGE":
                return "DogeCoin";
            case "UAH":
                return ";";
            case "ETH":
                return "Ethereum";
            case "NVC":
                return "Novacoin";
            case "PPC":
                return "Peercoin";
            case "XRP":
                return "Ripple";
            default:
                return ";";
        }
    }

    public static String getCryptoCurrencyId(String code) {
        switch (code) {
            case "BTC":
                return "bitcoin";
            case "LTC":
                return "litecoin";
            case "NMC":
                return "namecoin";
            case "DOGE":
                return "dogecoin";
            case "ETH":
                return "ethereum";
            case "NVC":
                return "novacoin";
            case "PPC":
                return "peercoin";
            case "BCH":
                return "bitcoin-cash";
            case "XRP":
                return "ripple";
            default:
                return "";
        }
    }
}
