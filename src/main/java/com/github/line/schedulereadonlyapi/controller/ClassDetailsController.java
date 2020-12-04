package com.github.line.schedulereadonlyapi.controller;

import com.github.line.schedulereadonlyapi.domain.ClassDetails;
import com.github.line.schedulereadonlyapi.service.ClassDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ClassDetailsController {
    private final ClassDetailsService service;

    @GetMapping(value = "/grouped-daily-schedules/{groupedDailyScheduleId}/class-details")
    public CollectionModel<EntityModel<ClassDetails>> allByGroupedDailyScheduleId(@PathVariable Long groupedDailyScheduleId) {
        return service.allByGroupedDailyScheduleId(groupedDailyScheduleId);
    }

    @GetMapping(value = "/class-details/{classDetailsId}")
    public EntityModel<ClassDetails> one(@PathVariable Long classDetailsId) {
        return service.one(classDetailsId);
    }

}
