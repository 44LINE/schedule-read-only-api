package com.github.line.schedulereadonlyapi.controller;

import com.github.line.schedulereadonlyapi.domain.ClassDetails;
import com.github.line.schedulereadonlyapi.service.ClassDetailsService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClassDetailsController {
    private final ClassDetailsService classDetailsService;

    private ClassDetailsController() {
        throw new AssertionError();
    }

    public ClassDetailsController(ClassDetailsService classDetailsService) {
        this.classDetailsService = classDetailsService;
    }

    @GetMapping(value = "/schedule/{scheduleId}/grouped-daily-schedule/{groupedDailyScheduleId}/class-details")
    public CollectionModel<EntityModel<ClassDetails>> all(@PathVariable Long scheduleId, @PathVariable Long groupedDailyScheduleId) {
        return classDetailsService.allByGroupedDailySchedule(scheduleId, groupedDailyScheduleId);
    }

    @GetMapping(value = "/schedule/{scheduleId}/grouped-daily-schedule/{groupedDailyScheduleId}/class-details/{classDetailsId}")
    public EntityModel<ClassDetails> one(@PathVariable Long scheduleId, @PathVariable Long groupedDailyScheduleId, @PathVariable Long classDetailsId) {
        return classDetailsService.oneByGroupedDailySchedule(scheduleId, groupedDailyScheduleId, classDetailsId);
    }

}
