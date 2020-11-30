package com.github.line.schedulereadonlyapi.controller;

import com.github.line.schedulereadonlyapi.domain.ScheduleVersion;
import com.github.line.schedulereadonlyapi.service.ScheduleVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScheduleVersionController {
    private final ScheduleVersionService scheduleVersionService;

    public ScheduleVersionController(@Autowired ScheduleVersionService scheduleVersionService) {
        this.scheduleVersionService = scheduleVersionService;
    }

    @GetMapping(value = "/schedule-versions")
    public CollectionModel<EntityModel<ScheduleVersion>> all() {
        return scheduleVersionService.all();
    }

    @GetMapping(value = "/schedule-versions/{id}")
    public EntityModel<ScheduleVersion> one(@PathVariable Long id) {
        return scheduleVersionService.one(id);
    }

    @GetMapping(value = "/schedule-versions/latest")
    public EntityModel<ScheduleVersion> latest() {
        return scheduleVersionService.latest();
    }
}
