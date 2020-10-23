package com.github.line.schedulereadonlyapi.controller;

import com.github.line.schedulereadonlyapi.domain.GroupedDailySchedule;
import com.github.line.schedulereadonlyapi.service.GroupedDailyScheduleService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GroupedDailyScheduleController {
    private final GroupedDailyScheduleService groupedDailyScheduleService;

    private GroupedDailyScheduleController() {
        throw new AssertionError();
    }

    public GroupedDailyScheduleController(GroupedDailyScheduleService groupedDailyScheduleService) {
        this.groupedDailyScheduleService = groupedDailyScheduleService;
    }

    @GetMapping(value = "/schedules/{id}/grouped-daily-schedule")
    public CollectionModel<EntityModel<GroupedDailySchedule>> all(@PathVariable Long id) {
        return groupedDailyScheduleService.all(id);
    }

    @GetMapping(value = "/schedules/{scheduleId}/grouped-daily-schedule/{groupedDailyScheduleId}")
    public EntityModel<GroupedDailySchedule> one(@PathVariable Long scheduleId, @PathVariable Long groupedDailyScheduleId) {
        return groupedDailyScheduleService.one(scheduleId, groupedDailyScheduleId);
    }
}
