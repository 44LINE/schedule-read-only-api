package com.github.line.schedulereadonlyapi.repository.readonly;

import com.github.line.schedulereadonlyapi.domain.Schedule;
import org.springframework.data.jpa.repository.Query;

public interface ScheduleRepository extends ReadOnlyJpaRepository<Schedule> {
    @Query(value = "SELECT c1 FROM Schedule AS c1 WHERE c1.isLatest = true")
    public Schedule getLatest();
}