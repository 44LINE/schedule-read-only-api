package com.github.line.schedulereadonlyapi.repository.readonly;

import com.github.line.schedulereadonlyapi.domain.GroupedDailySchedule;
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
        repository.getAllBySchedule_idAndGroupIdOrderByDateAsc(ID, ID)
                .forEach(groupedDailySchedule -> assertThat(groupedDailySchedule).isNotNull());
    }

    @Test
    void testFindONE() {
        GroupedDailySchedule bySchedule_idAndId = repository.getBySchedule_IdAndId(ID, ID);
        assertThat(bySchedule_idAndId).isNotNull();
    }

    @Test
    void testFindLatest() {
        repository.getAllBySchedule_isLatestTrue()
                .forEach(groupedDailySchedule -> assertThat(groupedDailySchedule).isNotNull());
    }

    @Test
    void testFindBySchedule() {
        repository.getAllBySchedule_Id(ID)
                .forEach(groupedDailySchedule -> assertThat(groupedDailySchedule).isNotNull());
    }

    @Test
    void testFindLatestByGroupChronologically() {
        repository.getAllByGroupIdAndSchedule_isLatestTrueOrderByDate(ID)
                .forEach(groupedDailySchedule -> assertThat(groupedDailySchedule).isNotNull());
    }
}