package com.github.line.schedulereadonlyapi.repository.readonly;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class GroupedDailyScheduleRepositoryTest {
    @Autowired
    private GroupedDailyScheduleRepository repository;

    private final Long ID = 1L;

    @Test
    void injectedComponentsAreNotNull() {
        assertThat(repository).isNotNull();
    }

    @Test
    void testFindByScheduleAndGroupChronologically() {
        repository.findByScheduleAndGroupChronologically(ID, ID)
                .forEach(groupedDailySchedule -> assertThat(groupedDailySchedule).isNotNull());
    }

    @Test
    void testFindLatest() {
        repository.findByLatestSchedule()
                .forEach(groupedDailySchedule -> assertThat(groupedDailySchedule).isNotNull());
    }

    @Test
    void testFindBySchedule() {
        repository.findByScheduleId(ID)
                .forEach(groupedDailySchedule -> assertThat(groupedDailySchedule).isNotNull());
    }

    @Test
    void testFindLatestByGroupChronologically() {
        repository.findLatestByGroupChronologically(ID)
                .forEach(groupedDailySchedule -> {
                        assertThat(groupedDailySchedule).isNotNull() ;});
    }
}