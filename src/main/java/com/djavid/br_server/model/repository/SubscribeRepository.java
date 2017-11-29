package com.djavid.br_server.model.repository;

import com.djavid.br_server.model.entity.Subscribe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface SubscribeRepository extends CrudRepository<Subscribe, Long> {
//    List<Subscribe> findSubscribesByCountryIdAndCurrId(String country_id, String curr_id);
    List<Subscribe> findSubscribesByTokenId(long token_id);
    List<Subscribe> findSubscribesByTickerId(long ticker_id);
}
