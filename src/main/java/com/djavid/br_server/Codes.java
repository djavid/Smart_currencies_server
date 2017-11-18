package com.djavid.br_server;


public class Codes {

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

    public static String[] crypto_coins = {"BTC", "BCH", "LTC", "ETH", "NVC", "NMC", "PPC", "DOGE"};

    public static String[] country_coins = {"USD", "EUR", "CAD", "CNY", "JPY", "PLN", "GBP", "RUB", "UAH"};

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
            default:
                return "";
        }
    }
}
