package com.github.line.schedulereadonlyapi.repository.readonly;

import com.github.line.schedulereadonlyapi.domain.Schedule;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends ReadOnlyJpaRepository<Schedule> {
    @Query("SELECT c1 FROM Schedule AS c1 WHERE c1.isLatest = true")
    Schedule findLatest();
}