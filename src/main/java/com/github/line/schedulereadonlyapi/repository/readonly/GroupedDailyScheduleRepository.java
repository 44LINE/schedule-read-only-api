package com.github.line.schedulereadonlyapi.repository.readonly;

import com.github.line.schedulereadonlyapi.domain.GroupedDailySchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupedDailyScheduleRepository extends JpaRepository<GroupedDailySchedule, Long> {
}
