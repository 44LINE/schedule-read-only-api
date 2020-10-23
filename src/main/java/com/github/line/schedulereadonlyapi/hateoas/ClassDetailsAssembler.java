package com.github.line.schedulereadonlyapi.hateoas;

import com.github.line.schedulereadonlyapi.controller.ScheduleController;
import com.github.line.schedulereadonlyapi.domain.ClassDetails;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class ClassDetailsAssembler implements RepresentationModelAssembler<ClassDetails, EntityModel<ClassDetails>> {
    @Override
    public EntityModel<ClassDetails> toModel(ClassDetails entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(ScheduleController.class).one(entity.getId())).withSelfRel(),
                linkTo(methodOn(ScheduleController.class).all()).withRel("class-details")
        );
    }
}