package com.djavid.br_server.model.repository;

import com.djavid.br_server.model.entity.Ticker;
import org.springframework.data.repository.CrudRepository;


public interface TickerRepository extends CrudRepository<Ticker, Long> {

}
