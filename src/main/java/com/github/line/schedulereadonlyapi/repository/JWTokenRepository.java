package com.github.line.schedulereadonlyapi.repository;

import com.github.line.schedulereadonlyapi.domain.JWToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JWTokenRepository extends CrudRepository<JWToken, String> {
    void deleteByUsername(String username);
}
