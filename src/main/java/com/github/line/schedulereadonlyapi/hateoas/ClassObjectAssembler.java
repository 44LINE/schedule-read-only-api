package com.github.line.schedulereadonlyapi.hateoas;

import com.github.line.schedulereadonlyapi.controller.ClassObjectController;
import com.github.line.schedulereadonlyapi.domain.ClassObject;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class ClassObjectAssembler implements RepresentationModelAssembler<ClassObject, EntityModel<ClassObject>> {
    @Override
    public EntityModel<ClassObject> toModel(ClassObject entity) {
        return EntityModel.of(entity,
                    linkTo(methodOn(ClassObjectController.class).one(entity.getId())).withSelfRel(),
                    linkTo(methodOn(ClassObjectController.class).all()).withRel("class-objects")
        );
    }
}
