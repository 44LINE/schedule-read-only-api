package com.github.line.schedulereadonlyapi.controller;

import com.github.line.schedulereadonlyapi.domain.ClassDetails;
import com.github.line.schedulereadonlyapi.service.ClassDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClassDetailsController {
    private final ClassDetailsService service;

    public ClassDetailsController(@Autowired ClassDetailsService service) {
        this.service = service;
    }

    @GetMapping(value = "/schedules/{scheduleId}/grouped-daily-schedules/{groupedDailyScheduleId}/class-details")
    public CollectionModel<EntityModel<ClassDetails>> all(@PathVariable Long scheduleId, @PathVariable Long groupedDailyScheduleId) {
        return service.allByGroupedDailyScheduleId(groupedDailyScheduleId);
    }

    @GetMapping(value = "/schedules/{scheduleId}/grouped-daily-schedules/{groupedDailyScheduleId}/class-details/{classDetailsId}")
    public EntityModel<ClassDetails> one(@PathVariable Long scheduleId, @PathVariable Long groupedDailyScheduleId, @PathVariable Long classDetailsId) {
        return service.one(classDetailsId);
    }

}
