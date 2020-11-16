package com.github.line.schedulereadonlyapi.hateoas;

import com.github.line.schedulereadonlyapi.controller.GroupedDailyScheduleController;
import com.github.line.schedulereadonlyapi.controller.ScheduleController;
import com.github.line.schedulereadonlyapi.domain.Schedule;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class ScheduleAssembler implements RepresentationModelAssembler<Schedule, EntityModel<Schedule>> {
    @Override
    public EntityModel<Schedule> toModel(Schedule entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(ScheduleController.class).one(entity.getId())).withSelfRel(),
                linkTo(methodOn(GroupedDailyScheduleController.class).all(entity.getId())).withRel("groupedDailyScheduleList")
        );
    }

    @Override
    public CollectionModel<EntityModel<Schedule>> toCollectionModel(Iterable<? extends Schedule> entities) {
        List<EntityModel<Schedule>> listOfEntityModel = new ArrayList<>();

        for (Schedule entity: entities) {
            listOfEntityModel.add(toModel(entity));
        }

        return CollectionModel.of(listOfEntityModel,
                linkTo(methodOn(ScheduleController.class).all()).withSelfRel());
    }
}
