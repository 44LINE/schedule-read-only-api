package com.github.line.schedulereadonlyapi.repository.readonly;

import com.github.line.schedulereadonlyapi.domain.ScheduleVersion;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ScheduleVersionRepositoryTest {

    @Autowired
    private ScheduleVersionRepository repository;

    @Test
    void testFindLatest() {
        ScheduleVersion latest = repository.getLatest();
        assertThat(latest).isNotNull();
    }
}