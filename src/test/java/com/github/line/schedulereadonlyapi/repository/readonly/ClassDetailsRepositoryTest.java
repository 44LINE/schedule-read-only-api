package com.github.line.schedulereadonlyapi.repository.readonly;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ClassDetailsRepositoryTest {
    @Autowired
    private ClassDetailsRepository repository;

    private final Long ID = 1L;

    @Test
    void injectedComponentsAreNotNull() {
        assertThat(repository).isNotNull();
    }

    @Test
    void testFindByGroupedDailySchedule() {
        repository.findByGroupedDailySchedule(ID)
            .forEach(classDetails -> {
                assertThat(classDetails).isNotNull();
            });
    }
}