package com.github.line.schedulereadonlyapi.hateoas;

import com.github.line.schedulereadonlyapi.controller.LecturerController;
import com.github.line.schedulereadonlyapi.domain.api.Lecturer;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class LecturerAssembler implements RepresentationModelAssembler<Lecturer, EntityModel<Lecturer>> {
    @Override
    public EntityModel<Lecturer> toModel(Lecturer entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(LecturerController.class).one(entity.getId())).withSelfRel(),
                linkTo(methodOn(LecturerController.class).all()).withRel("lecturers")
        );
    }

    @Override
    public CollectionModel<EntityModel<Lecturer>> toCollectionModel(Iterable<? extends Lecturer> entities) {
        List<EntityModel<Lecturer>> lecturers = new ArrayList<>();

        for (Lecturer lecturer: entities) {
            lecturers.add(EntityModel.of(lecturer,
                    linkTo(methodOn(LecturerController.class).one(lecturer.getId())).withSelfRel()));
        }

        return CollectionModel.of(lecturers,
                linkTo(methodOn(LecturerController.class).all()).withSelfRel());
    }
}
