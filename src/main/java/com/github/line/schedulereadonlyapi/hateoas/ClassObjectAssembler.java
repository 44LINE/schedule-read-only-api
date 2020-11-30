package com.github.line.schedulereadonlyapi.hateoas;

import com.github.line.schedulereadonlyapi.controller.ClassObjectController;
import com.github.line.schedulereadonlyapi.domain.ClassObject;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ClassObjectAssembler implements RepresentationModelAssembler<ClassObject, EntityModel<ClassObject>> {
    @Override
    public EntityModel<ClassObject> toModel(ClassObject entity) {
        return EntityModel.of(entity,
                    linkTo(methodOn(ClassObjectController.class).one(entity.getId())).withSelfRel(),
                    linkTo(methodOn(ClassObjectController.class).all()).withRel("class-objects")
        );
    }

    @Override
    public CollectionModel<EntityModel<ClassObject>> toCollectionModel(Iterable<? extends ClassObject> entities) {
        List<EntityModel<ClassObject>> classObjects = new ArrayList<>();

        for (ClassObject classObject: entities) {
            classObjects.add(EntityModel.of(classObject,
                    linkTo(methodOn(ClassObjectController.class).one(classObject.getId())).withSelfRel()));
        }

        return CollectionModel.of(classObjects,
                linkTo(methodOn(ClassObjectController.class).all()).withSelfRel());
    }
}
