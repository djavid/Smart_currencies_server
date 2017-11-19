package com.djavid.br_server.model.repository;

import com.djavid.br_server.model.entity.RegistrationToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RegistrationTokenRepository extends CrudRepository<RegistrationToken, Long> {
    RegistrationToken findRegistrationTokenByToken(String token);
    RegistrationToken findRegistrationTokenById(Long id);
}
