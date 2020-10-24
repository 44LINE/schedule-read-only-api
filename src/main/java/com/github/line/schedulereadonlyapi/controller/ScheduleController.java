package com.github.line.schedulereadonlyapi.controller;

import com.github.line.schedulereadonlyapi.domain.Schedule;
import com.github.line.schedulereadonlyapi.service.ScheduleService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class ScheduleController {
    private final ScheduleService scheduleService;

    private ScheduleController() {
        throw new AssertionError();
    }

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping(value = "/schedules")
    public CollectionModel<EntityModel<Schedule>> all() {
        return scheduleService.all();
    }

    @GetMapping(value = "/schedules/{id}")
    public EntityModel<Schedule> one(@PathVariable Long id) {
        return scheduleService.one(id);
    }
}
