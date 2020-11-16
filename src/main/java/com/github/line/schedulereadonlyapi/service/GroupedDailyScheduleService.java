package com.github.line.schedulereadonlyapi.service;

import com.github.line.schedulereadonlyapi.domain.GroupedDailySchedule;
import com.github.line.schedulereadonlyapi.hateoas.GroupedDailyScheduleAssembler;
import com.github.line.schedulereadonlyapi.repository.readonly.GroupedDailyScheduleRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

public class GroupedDailyScheduleService {
    private final GroupedDailyScheduleRepository groupedDailyScheduleRepository;
    private final GroupedDailyScheduleAssembler groupedDailyScheduleAssembler;

    private GroupedDailyScheduleService() {
        throw new AssertionError();
    }

    public GroupedDailyScheduleService(GroupedDailyScheduleRepository groupedDailyScheduleRepository, GroupedDailyScheduleAssembler groupedDailyScheduleAssembler) {
        this.groupedDailyScheduleRepository = groupedDailyScheduleRepository;
        this.groupedDailyScheduleAssembler = groupedDailyScheduleAssembler;
    }

    public CollectionModel<EntityModel<GroupedDailySchedule>> all(Long scheduleId) {
        return groupedDailyScheduleAssembler.toCollectionModel(groupedDailyScheduleRepository.getAllBySchedule_Id(scheduleId));
    }

    public EntityModel<GroupedDailySchedule> one(Long scheduleId, Long groupedDailyScheduleId) {
        return groupedDailyScheduleAssembler.toModel(groupedDailyScheduleRepository.getBySchedule_IdAndId(scheduleId, groupedDailyScheduleId));
    }

    public CollectionModel<EntityModel<GroupedDailySchedule>> allByGroupId(Long scheduleId, Long groupId) {
        return groupedDailyScheduleAssembler.toCollectionModel(groupedDailyScheduleRepository
                .getAllBySchedule_idAndGroupIdOrderByDateAsc(scheduleId, groupId));
    }

    public CollectionModel<EntityModel<GroupedDailySchedule>> allLatest() {
        return groupedDailyScheduleAssembler.toCollectionModel(groupedDailyScheduleRepository.getAllBySchedule_isLatestTrue());
    }

    public CollectionModel<EntityModel<GroupedDailySchedule>> allLatestByGroupId(Long groupId) {
        return groupedDailyScheduleAssembler.toCollectionModel(groupedDailyScheduleRepository
                .getAllByGroupIdAndSchedule_isLatestTrueOrderByDate(groupId));
    }
}
