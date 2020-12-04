package com.github.line.schedulereadonlyapi.controller;

import com.github.line.schedulereadonlyapi.domain.ScheduleVersion;
import com.github.line.schedulereadonlyapi.service.ScheduleVersionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScheduleVersionController {
    private final ScheduleVersionService service;

    @GetMapping(value = "/schedule-versions")
    public CollectionModel<EntityModel<ScheduleVersion>> all() {
        return service.all();
    }

    @GetMapping(value = "/schedule-versions/{id}")
    public EntityModel<ScheduleVersion> one(@PathVariable Long id) {
        return service.one(id);
    }

    @GetMapping(value = "/schedule-versions/latest")
    public EntityModel<ScheduleVersion> latest() {
        return service.latest();
    }
}
