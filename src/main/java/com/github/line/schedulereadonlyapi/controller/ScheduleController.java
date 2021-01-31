package com.github.line.schedulereadonlyapi.controller;

import com.github.line.schedulereadonlyapi.domain.api.Schedule;
import com.github.line.schedulereadonlyapi.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/schedules")
public class ScheduleController {
    private final ScheduleService service;

    @GetMapping
    public CollectionModel<EntityModel<Schedule>> all() {
        return service.all();
    }

    @GetMapping(value = "/{id}")
    public EntityModel<Schedule> one(@PathVariable Long id) {
        return service.one(id);
    }

    @GetMapping(value = "/latest")
    public EntityModel<Schedule> latest() {
        return service.latest();
    }
}
