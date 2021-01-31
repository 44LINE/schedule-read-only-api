package com.github.line.schedulereadonlyapi.controller;

import com.github.line.schedulereadonlyapi.domain.api.ScheduleVersion;
import com.github.line.schedulereadonlyapi.service.ScheduleVersionService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/schedule-versions")
public class ScheduleVersionController {
    private final ScheduleVersionService service;

    @GetMapping
    public CollectionModel<EntityModel<ScheduleVersion>> all() {
        return service.all();
    }

    @GetMapping(value = "/{id}")
    public EntityModel<ScheduleVersion> one(@PathVariable Long id) {
        return service.one(id);
    }

    @GetMapping(value = "/latest")
    public EntityModel<ScheduleVersion> latest() {
        return service.latest();
    }
}
