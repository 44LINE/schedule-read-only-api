package com.github.line.schedulereadonlyapi.repository.readonly;

import com.github.line.schedulereadonlyapi.domain.Schedule;

public interface ScheduleRepository extends ReadOnlyJpaRepository<Schedule> {
    public Schedule getLatest();
}