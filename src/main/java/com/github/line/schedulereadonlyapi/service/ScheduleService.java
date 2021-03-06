package com.github.line.schedulereadonlyapi.service;

import com.github.line.schedulereadonlyapi.domain.api.Schedule;
import com.github.line.schedulereadonlyapi.repository.readonly.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository repository;
    private final RepresentationModelAssembler<Schedule, EntityModel<Schedule>> assembler;

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
