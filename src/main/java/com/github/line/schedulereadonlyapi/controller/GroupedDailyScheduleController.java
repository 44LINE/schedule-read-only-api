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

    @GetMapping(value = "/schedules/{id}/grouped-daily-schedules")
    public CollectionModel<EntityModel<GroupedDailySchedule>> all(@PathVariable Long id) {
        return groupedDailyScheduleService.all(id);
    }

    @GetMapping(value = "/schedules/{scheduleId}/grouped-daily-schedules/{groupedDailyScheduleId}")
    public EntityModel<GroupedDailySchedule> one(@PathVariable Long scheduleId, @PathVariable Long groupedDailyScheduleId) {
        return groupedDailyScheduleService.one(scheduleId, groupedDailyScheduleId);
    }

    @GetMapping(value = "/schedules/{scheduleId}/grouped-daily-schedules/group-id/{groupId}")
    public CollectionModel<EntityModel<GroupedDailySchedule>> allByGroupId(@PathVariable Long scheduleId, @PathVariable Long groupId) {
        return groupedDailyScheduleService.allByGroupId(scheduleId, groupId - 1);
    }

    @GetMapping(value = "/schedules/latest/grouped-daily-schedules")
    public CollectionModel<EntityModel<GroupedDailySchedule>> allLatest() {
        return groupedDailyScheduleService.allLatest();
    }

    @GetMapping(value = "/schedules/latest/grouped-daily-schedules/group-id/{groupId}")
    public CollectionModel<EntityModel<GroupedDailySchedule>> allLatestByGroupId(@PathVariable Long groupId) {
        return groupedDailyScheduleService.allLatestByGroupId(groupId - 1);
    }
}
