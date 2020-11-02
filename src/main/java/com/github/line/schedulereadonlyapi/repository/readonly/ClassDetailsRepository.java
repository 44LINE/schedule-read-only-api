package com.github.line.schedulereadonlyapi.repository.readonly;

import com.github.line.schedulereadonlyapi.domain.ClassDetails;

import java.util.List;

public interface ClassDetailsRepository extends ReadOnlyJpaRepository<ClassDetails> {
    ClassDetails getByGroupedDailySchedule_Schedule_IdAndGroupedDailySchedule_IdAndId(
            Long scheduleId, Long groupedDailyScheduleId, Long classDetailsId);

    List<ClassDetails> getAllByGroupedDailySchedule_Schedule_IdAndGroupedDailySchedule_Id(
            Long scheduleId, Long groupedDailyScheduleId);
}
