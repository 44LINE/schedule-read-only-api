package com.github.line.schedulereadonlyapi.hateoas;

import com.github.line.schedulereadonlyapi.controller.ScheduleController;
import com.github.line.schedulereadonlyapi.domain.GroupedDailySchedule;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class GroupedDailyScheduleAssembler implements RepresentationModelAssembler<GroupedDailySchedule, EntityModel<GroupedDailySchedule>> {
    @Override
    public EntityModel<GroupedDailySchedule> toModel(GroupedDailySchedule entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(ScheduleController.class).one(entity.getId())).withSelfRel(),
                linkTo(methodOn(ScheduleController.class).all()).withRel("grouped-daily-schedules")
        );
    }
}
