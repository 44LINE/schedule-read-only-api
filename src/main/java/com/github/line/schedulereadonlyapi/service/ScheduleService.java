package com.github.line.schedulereadonlyapi.service;

import com.github.line.schedulereadonlyapi.domain.Schedule;
import com.github.line.schedulereadonlyapi.repository.readonly.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final RepresentationModelAssembler<Schedule, EntityModel<Schedule>> scheduleAssembler;

    public ScheduleService(@Autowired ScheduleRepository scheduleRepository,
                           @Autowired RepresentationModelAssembler<Schedule, EntityModel<Schedule>> scheduleAssembler) {
        this.scheduleRepository = scheduleRepository;
        this.scheduleAssembler = scheduleAssembler;
    }

    public CollectionModel<EntityModel<Schedule>> all() {
        return scheduleAssembler.toCollectionModel(scheduleRepository.findAll());
    }

    public EntityModel<Schedule> one(Long scheduleId) {
        return scheduleAssembler.toModel(scheduleRepository.findById(scheduleId).get());
    }

    public EntityModel<Schedule> latest() {
        return scheduleAssembler.toModel(scheduleRepository.findLatest());
    }

}
