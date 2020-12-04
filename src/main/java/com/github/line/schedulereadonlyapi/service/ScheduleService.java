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
    private final ScheduleRepository repository;
    private final RepresentationModelAssembler<Schedule, EntityModel<Schedule>> assembler;

    public ScheduleService(@Autowired ScheduleRepository repository,
                           @Autowired RepresentationModelAssembler<Schedule, EntityModel<Schedule>> assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    public EntityModel<Schedule> one(Long scheduleId) {
        return assembler.toModel(repository.getOne(scheduleId));
    }

    public CollectionModel<EntityModel<Schedule>> all() {
        return assembler.toCollectionModel(repository.findAll());
    }

    public EntityModel<Schedule> latest() {
        return assembler.toModel(repository.findLatest());
    }

}
