package com.github.line.schedulereadonlyapi.repository.readonly;

import com.github.line.schedulereadonlyapi.domain.GroupedDailySchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupedDailyScheduleRepository extends JpaRepository<GroupedDailySchedule, Long> {
    public GroupedDailySchedule getBySchedule_IdAndId(Long scheduleId, Long groupedDailyScheduleId);
    public List<GroupedDailySchedule> getAllBySchedule_Id(Long scheduleId);
}
