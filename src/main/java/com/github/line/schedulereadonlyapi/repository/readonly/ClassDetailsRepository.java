package com.github.line.schedulereadonlyapi.repository.readonly;

import com.github.line.schedulereadonlyapi.domain.api.ClassDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassDetailsRepository extends ReadOnlyJpaRepository<ClassDetails> {

    @Query("SELECT cd FROM ClassDetails AS cd WHERE cd.groupedDailySchedule.id = :groupedDailyScheduleId")
    List<ClassDetails> findByGroupedDailySchedule(Long groupedDailyScheduleId);
}
