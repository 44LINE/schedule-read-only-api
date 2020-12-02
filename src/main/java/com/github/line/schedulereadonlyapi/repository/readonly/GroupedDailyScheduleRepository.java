package com.github.line.schedulereadonlyapi.repository.readonly;

import com.github.line.schedulereadonlyapi.domain.GroupedDailySchedule;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupedDailyScheduleRepository extends ReadOnlyJpaRepository<GroupedDailySchedule> {

    List<GroupedDailySchedule> findByScheduleId(Long scheduleId);

    @Query("SELECT gds FROM GroupedDailySchedule AS gds WHERE gds.schedule.id = :scheduleId AND gds.groupId = :groupId ORDER BY gds.date")
    List<GroupedDailySchedule> findByScheduleAndGroupChronologically(Long scheduleId, Long groupId);

    @Query("SELECT gds FROM GroupedDailySchedule AS gds WHERE gds.schedule.isLatest = true")
    List<GroupedDailySchedule> findByLatestSchedule();

    @Query("SELECT gds FROM GroupedDailySchedule AS gds WHERE gds.schedule.isLatest = true AND gds.groupId = :groupId ORDER BY gds.date")
    List<GroupedDailySchedule> findLatestByGroupChronologically(Long groupId);
}
