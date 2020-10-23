package com.github.line.schedulereadonlyapi.controller;

import com.github.line.schedulereadonlyapi.domain.ScheduleVersion;
import com.github.line.schedulereadonlyapi.service.ScheduleVersionService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScheduleVersionController {
    private final ScheduleVersionService scheduleVersionService;

    private ScheduleVersionController() {
        throw new AssertionError();
    }

    public ScheduleVersionController(ScheduleVersionService scheduleVersionService) {
        this.scheduleVersionService = scheduleVersionService;
    }

    @GetMapping(value = "/schedule-version")
    public CollectionModel<EntityModel<ScheduleVersion>> all() {
        return scheduleVersionService.all();
    }

    @GetMapping(value = "/schedule-version/latest")
    public EntityModel<ScheduleVersion> latest() {
        return scheduleVersionService.latest();
    }
}
