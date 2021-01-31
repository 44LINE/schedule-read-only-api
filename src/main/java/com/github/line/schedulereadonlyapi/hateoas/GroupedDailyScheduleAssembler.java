package com.github.line.schedulereadonlyapi.hateoas;

import com.github.line.schedulereadonlyapi.controller.ClassDetailsController;
import com.github.line.schedulereadonlyapi.controller.GroupedDailyScheduleController;
import com.github.line.schedulereadonlyapi.domain.api.GroupedDailySchedule;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class GroupedDailyScheduleAssembler implements RepresentationModelAssembler<GroupedDailySchedule, EntityModel<GroupedDailySchedule>> {
    @Override
    public EntityModel<GroupedDailySchedule> toModel(GroupedDailySchedule entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(GroupedDailyScheduleController.class).one(entity.getId())).withSelfRel(),
                linkTo(methodOn(ClassDetailsController.class).allByGroupedDailyScheduleId(entity.getId())).withRel("classDetailsList"));
    }

    @Override
    public CollectionModel<EntityModel<GroupedDailySchedule>> toCollectionModel(Iterable<? extends GroupedDailySchedule> entities) {
        List<EntityModel<GroupedDailySchedule>> listOfEntityModel = new ArrayList<>();
        GroupedDailySchedule firstEntity = entities.iterator().next();

        for (GroupedDailySchedule entity: entities) {
            listOfEntityModel.add(toModel(entity));
        }

        return CollectionModel.of(listOfEntityModel,
                linkTo(methodOn(GroupedDailyScheduleController.class).all(firstEntity.getSchedule().getId())).withSelfRel());
    }
}
