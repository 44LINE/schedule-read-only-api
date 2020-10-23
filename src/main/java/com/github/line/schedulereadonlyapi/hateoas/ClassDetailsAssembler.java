package com.github.line.schedulereadonlyapi.hateoas;

import com.github.line.schedulereadonlyapi.controller.ClassDetailsController;
import com.github.line.schedulereadonlyapi.controller.GroupedDailyScheduleController;
import com.github.line.schedulereadonlyapi.domain.ClassDetails;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class ClassDetailsAssembler implements RepresentationModelAssembler<ClassDetails, EntityModel<ClassDetails>> {
    @Override
    public EntityModel<ClassDetails> toModel(ClassDetails entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(ClassDetailsController.class).one(entity.getGroupedDailySchedule().getSchedule().getId(),
                                                                  entity.getGroupedDailySchedule().getId(),
                                                                  entity.getId())).withSelfRel(),
                linkTo(methodOn(ClassDetailsController.class).all(entity.getGroupedDailySchedule().getSchedule().getId(),
                                                                  entity.getGroupedDailySchedule().getId())).withRel("class-details"));
    }

    @Override
    public CollectionModel<EntityModel<ClassDetails>> toCollectionModel(Iterable<? extends ClassDetails> entities) {
        List<EntityModel<ClassDetails>> listOfEntityModel = new ArrayList<>();
        ClassDetails firstEntity = entities.iterator().next();

        for (ClassDetails entity: entities) {
            listOfEntityModel.add(EntityModel.of(entity,
                    linkTo(methodOn(ClassDetailsController.class).one(entity.getGroupedDailySchedule().getSchedule().getId(),
                                                                      entity.getGroupedDailySchedule().getId(),
                                                                      entity.getId())).withSelfRel()));
        }

        return CollectionModel.of(listOfEntityModel,
                linkTo(methodOn(ClassDetailsController.class).all(firstEntity.getGroupedDailySchedule().getSchedule().getId(),
                        firstEntity.getGroupedDailySchedule().getId())).withRel("class-details"),
                linkTo(methodOn(GroupedDailyScheduleController.class).one(firstEntity.getGroupedDailySchedule().getSchedule().getId(),
                        firstEntity.getGroupedDailySchedule().getId())).withRel("grouped-daily-schedule"));
    }
}