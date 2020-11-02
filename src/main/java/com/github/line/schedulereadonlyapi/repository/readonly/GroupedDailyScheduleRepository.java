package com.github.line.schedulereadonlyapi.repository.readonly;

import com.github.line.schedulereadonlyapi.domain.GroupedDailySchedule;

import java.util.List;

public interface GroupedDailyScheduleRepository extends ReadOnlyJpaRepository<GroupedDailySchedule> {
    GroupedDailySchedule getBySchedule_IdAndId(Long scheduleId, Long groupedDailyScheduleId);
    List<GroupedDailySchedule> getAllBySchedule_Id(Long scheduleId);
}
