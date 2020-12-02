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
    private final GroupedDailyScheduleRepository groupedDailyScheduleRepository;
    private final RepresentationModelAssembler<GroupedDailySchedule, EntityModel<GroupedDailySchedule>> groupedDailyScheduleAssembler;

    public GroupedDailyScheduleService(@Autowired GroupedDailyScheduleRepository groupedDailyScheduleRepository,
                                       @Autowired RepresentationModelAssembler<GroupedDailySchedule, EntityModel<GroupedDailySchedule>> groupedDailyScheduleAssembler) {
        this.groupedDailyScheduleRepository = groupedDailyScheduleRepository;
        this.groupedDailyScheduleAssembler = groupedDailyScheduleAssembler;
    }

    public CollectionModel<EntityModel<GroupedDailySchedule>> all(Long scheduleId) {
        return groupedDailyScheduleAssembler.toCollectionModel(groupedDailyScheduleRepository.findByScheduleId(scheduleId));
    }

    public EntityModel<GroupedDailySchedule> one(Long scheduleId, Long groupedDailyScheduleId) {
        return groupedDailyScheduleAssembler.toModel(groupedDailyScheduleRepository.getOne(groupedDailyScheduleId));
    }

    public CollectionModel<EntityModel<GroupedDailySchedule>> allByGroupId(Long scheduleId, Long groupId) {
        return groupedDailyScheduleAssembler.toCollectionModel(groupedDailyScheduleRepository
                .findByScheduleAndGroupChronologically(scheduleId, groupId));
    }

    public CollectionModel<EntityModel<GroupedDailySchedule>> allLatest() {
        return groupedDailyScheduleAssembler.toCollectionModel(groupedDailyScheduleRepository.findByLatestSchedule());
    }

    public CollectionModel<EntityModel<GroupedDailySchedule>> allLatestByGroupId(Long groupId) {
        return groupedDailyScheduleAssembler.toCollectionModel(groupedDailyScheduleRepository
                .findLatestByGroupChronologically(groupId));
    }
}
