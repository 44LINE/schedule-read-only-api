package com.github.line.schedulereadonlyapi.controller;

import com.github.line.schedulereadonlyapi.domain.GroupedDailySchedule;
import com.github.line.schedulereadonlyapi.service.GroupedDailyScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GroupedDailyScheduleController {
    private final GroupedDailyScheduleService service;

    @GetMapping(value = "/grouped-daily-schedules/{groupedDailyScheduleId}")
    public EntityModel<GroupedDailySchedule> one(@PathVariable Long groupedDailyScheduleId) {
        return service.one(groupedDailyScheduleId);
    }

    @GetMapping(value = "/schedules/{id}/grouped-daily-schedules")
    public CollectionModel<EntityModel<GroupedDailySchedule>> all(@PathVariable Long id) {
        return service.allByScheduleId(id);
    }

    @GetMapping(value = "/schedules/latest/grouped-daily-schedules")
    public CollectionModel<EntityModel<GroupedDailySchedule>> allLatest() {
        return service.allLatest();
    }

    @GetMapping(value = "/schedules/{scheduleId}/grouped-daily-schedules/group-id/{groupId}")
    public CollectionModel<EntityModel<GroupedDailySchedule>> allByGroupId(@PathVariable Long scheduleId, @PathVariable Long groupId) {
        return service.allByScheduleIdAndGroupIdSorted(scheduleId, groupId - 1);
    }

    @GetMapping(value = "/schedules/latest/grouped-daily-schedules/group-id/{groupId}")
    public CollectionModel<EntityModel<GroupedDailySchedule>> allLatestByGroupId(@PathVariable Long groupId) {
        return service.allLatestByGroupIdSorted(groupId - 1);
    }
}
