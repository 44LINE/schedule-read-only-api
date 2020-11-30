package com.github.line.schedulereadonlyapi.hateoas;

import com.github.line.schedulereadonlyapi.controller.ClassDetailsController;
import com.github.line.schedulereadonlyapi.controller.ClassObjectController;
import com.github.line.schedulereadonlyapi.controller.GroupedDailyScheduleController;
import com.github.line.schedulereadonlyapi.controller.LecturerController;
import com.github.line.schedulereadonlyapi.domain.ClassDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ClassDetailsAssembler implements RepresentationModelAssembler<ClassDetails, EntityModel<ClassDetails>> {
    @Override
    public EntityModel<ClassDetails> toModel(ClassDetails entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(ClassDetailsController.class).one(entity.getGroupedDailySchedule().getSchedule().getId(),
                                                                  entity.getGroupedDailySchedule().getId(),
                                                                  entity.getId())).withSelfRel(),
                linkTo(methodOn(ClassObjectController.class).one(entity.getClassObject().getId())).withRel("classObject"),
                linkTo(methodOn(LecturerController.class).one(entity.getLecturer().getId())).withRel("lecturer"));
    }

    @Override
    public CollectionModel<EntityModel<ClassDetails>> toCollectionModel(Iterable<? extends ClassDetails> entities) {
        List<EntityModel<ClassDetails>> listOfEntityModel = new ArrayList<>();
        ClassDetails firstEntity = entities.iterator().next();

        for (ClassDetails entity: entities) {
            listOfEntityModel.add(toModel(entity));
        }

        return CollectionModel.of(listOfEntityModel,
                linkTo(methodOn(ClassDetailsController.class).all(firstEntity.getGroupedDailySchedule().getSchedule().getId(),
                        firstEntity.getGroupedDailySchedule().getId())).withSelfRel());
    }
}