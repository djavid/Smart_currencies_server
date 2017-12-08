package com.djavid.br_server.model.repository;

import com.djavid.br_server.model.entity.CurrencyUpdate;
import com.djavid.br_server.model.entity.Ticker;
import org.springframework.data.repository.CrudRepository;


public interface CurrencyUpdateRepository extends CrudRepository<CurrencyUpdate, Long> {
    CurrencyUpdate findCurrencyUpdateByCountryId(String country_id);
    CurrencyUpdate findCurrencyUpdateByCryptoId(String crypto_id);
    CurrencyUpdate findCurrencyUpdateByCryptoIdAndCountryId(String crypto_id, String country_id);
}
