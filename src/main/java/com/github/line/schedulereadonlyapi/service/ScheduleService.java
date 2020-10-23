package com.github.line.schedulereadonlyapi.service;

import com.github.line.schedulereadonlyapi.domain.Schedule;
import com.github.line.schedulereadonlyapi.hateoas.ScheduleAssembler;
import com.github.line.schedulereadonlyapi.repository.readonly.ScheduleRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final ScheduleAssembler scheduleAssembler;

    private ScheduleService() {
        throw new AssertionError();
    }

    public ScheduleService(ScheduleRepository scheduleRepository, ScheduleAssembler scheduleAssembler) {
        this.scheduleRepository = scheduleRepository;
        this.scheduleAssembler = scheduleAssembler;
    }

    public CollectionModel<EntityModel<Schedule>> all() {
        return scheduleAssembler.toCollectionModel(scheduleRepository.findAll());
    }

    public EntityModel<Schedule> one(Long scheduleId) {
        return scheduleAssembler.toModel(scheduleRepository.getOne(scheduleId));
    }
}
