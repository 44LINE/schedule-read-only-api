package com.github.line.schedulereadonlyapi.repository.readonly;

import com.github.line.schedulereadonlyapi.domain.ScheduleVersion;
import org.springframework.data.jpa.repository.Query;

public interface ScheduleVersionRepository extends ReadOnlyJpaRepository<ScheduleVersion> {
    @Query("SELECT c1 FROM ScheduleVersion AS c1 JOIN c1.schedule c2 WHERE c2.isLatest = TRUE")
    public ScheduleVersion getLatest();
}