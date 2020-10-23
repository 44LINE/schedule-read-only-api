package com.github.line.schedulereadonlyapi.hateoas;

import com.github.line.schedulereadonlyapi.controller.ScheduleController;
import com.github.line.schedulereadonlyapi.domain.ScheduleVersion;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class ScheduleVersionAssembler implements RepresentationModelAssembler<ScheduleVersion, EntityModel<ScheduleVersion>> {
    @Override
    public EntityModel<ScheduleVersion> toModel(ScheduleVersion entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(ScheduleController.class).one(entity.getId())).withSelfRel(),
                linkTo(methodOn(ScheduleController.class).all()).withRel("schedule-versions")
        );
    }
}