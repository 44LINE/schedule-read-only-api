package com.github.line.schedulereadonlyapi.repository.readonly;

import com.github.line.schedulereadonlyapi.domain.Schedule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ScheduleRepositoryTest {
    @Autowired
    private ScheduleRepository repository;

    @Test
    void injectedComponentsAreNotNull() {
        assertThat(repository).isNotNull();
    }

    @Test
    void testFindLatest() {
        Schedule latest = repository.findLatest();
        assertThat(latest).isNotNull();
    }
}