package com.github.line.schedulereadonlyapi.repository;

import com.github.line.schedulereadonlyapi.domain.ConfirmationToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, String> {
}
