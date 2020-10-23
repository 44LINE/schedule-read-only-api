package com.github.line.schedulereadonlyapi.hateoas;

import com.github.line.schedulereadonlyapi.controller.ScheduleController;
import com.github.line.schedulereadonlyapi.domain.Schedule;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class ScheduleAssembler implements RepresentationModelAssembler<Schedule, EntityModel<Schedule>> {
    @Override
    public EntityModel<Schedule> toModel(Schedule entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(ScheduleController.class).one(entity.getId())).withSelfRel(),
                linkTo(methodOn(ScheduleController.class).all()).withRel("schedules")
        );
    }
}
