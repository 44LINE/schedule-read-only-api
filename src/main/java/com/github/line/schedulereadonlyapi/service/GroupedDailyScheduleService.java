package com.github.line.schedulereadonlyapi.service;

import com.github.line.schedulereadonlyapi.domain.GroupedDailySchedule;
import com.github.line.schedulereadonlyapi.repository.readonly.GroupedDailyScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Service;

@Service
public class GroupedDailyScheduleService {
    private final GroupedDailyScheduleRepository repository;
    private final RepresentationModelAssembler<GroupedDailySchedule, EntityModel<GroupedDailySchedule>> assembler;

    public GroupedDailyScheduleService(@Autowired GroupedDailyScheduleRepository repository,
                                       @Autowired RepresentationModelAssembler<GroupedDailySchedule, EntityModel<GroupedDailySchedule>> assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    public EntityModel<GroupedDailySchedule> one(Long groupedDailyScheduleId) {
        return assembler.toModel(repository.getOne(groupedDailyScheduleId));
    }

    public CollectionModel<EntityModel<GroupedDailySchedule>> allByScheduleId(Long scheduleId) {
        return assembler.toCollectionModel(repository.findByScheduleId(scheduleId));
    }

    public CollectionModel<EntityModel<GroupedDailySchedule>> allLatest() {
        return assembler.toCollectionModel(repository.findByLatestSchedule());
    }

    public CollectionModel<EntityModel<GroupedDailySchedule>> allByScheduleIdAndGroupIdSorted(Long scheduleId, Long groupId) {
        return assembler.toCollectionModel(repository.findByScheduleAndGroupChronologically(scheduleId, groupId));
    }

    public CollectionModel<EntityModel<GroupedDailySchedule>> allLatestByGroupIdSorted(Long groupId) {
        return assembler.toCollectionModel(repository.findLatestByGroupChronologically(groupId));
    }
}
