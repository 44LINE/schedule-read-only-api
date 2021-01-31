package com.github.line.schedulereadonlyapi.service;

import com.github.line.schedulereadonlyapi.domain.api.ScheduleVersion;
import com.github.line.schedulereadonlyapi.repository.readonly.ScheduleVersionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleVersionService {
    private final ScheduleVersionRepository repository;
    private final RepresentationModelAssembler<ScheduleVersion, EntityModel<ScheduleVersion>> assembler;

    public EntityModel<ScheduleVersion> one(Long scheduleVersionId) {
        return assembler.toModel(repository.getOne(scheduleVersionId));
    }

    public CollectionModel<EntityModel<ScheduleVersion>> all() {
        return assembler.toCollectionModel(repository.findAll());
    }

    public EntityModel<ScheduleVersion> latest() {
        return assembler.toModel(repository.findLatest());
    }
}
