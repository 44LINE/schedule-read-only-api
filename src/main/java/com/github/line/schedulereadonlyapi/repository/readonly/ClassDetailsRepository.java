package com.github.line.schedulereadonlyapi.repository.readonly;

import com.github.line.schedulereadonlyapi.domain.ClassDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassDetailsRepository extends JpaRepository<ClassDetails, Long> {
    public ClassDetails getByGroupedDailySchedule_Schedule_IdAndGroupedDailySchedule_IdAndId(
            Long scheduleId, Long groupedDailyScheduleId, Long classDetailsId);

    public List<ClassDetails> getAllByGroupedDailySchedule_Schedule_IdAndGroupedDailySchedule_Id(
            Long scheduleId, Long groupedDailyScheduleId);
}
