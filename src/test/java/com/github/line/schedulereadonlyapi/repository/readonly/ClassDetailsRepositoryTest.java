package com.github.line.schedulereadonlyapi.repository.readonly;

import com.github.line.schedulereadonlyapi.domain.ClassDetails;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import java.util.List;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ClassDetailsRepositoryTest {
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired private DataSource dataSource;
    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private EntityManager entityManager;
    @Autowired private ClassDetailsRepository repository;

    private final Long ID = 1L;

    @Test
    void injectedComponentsAreNotNull() {
        assertThat(dataSource).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(repository).isNotNull();
    }

    @Test
    void testFindOneByGroupedDailySchedule() {
        repository.findByGroupedDailySchedule(ID).stream()
            .forEach(classDetails -> {
                assertThat(classDetails).isNotNull();
                logger.info(classDetails.toString());
            });
    }
}