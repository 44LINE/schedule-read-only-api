package com.github.line.schedulereadonlyapi.repository.readonly;

import com.github.line.schedulereadonlyapi.domain.api.ScheduleVersion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleVersionRepository extends ReadOnlyJpaRepository<ScheduleVersion> {

    @Query("SELECT sv FROM ScheduleVersion AS sv WHERE sv.schedule.isLatest = true")
    ScheduleVersion findLatest();
}