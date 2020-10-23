package com.github.line.schedulereadonlyapi.service;

import com.github.line.schedulereadonlyapi.domain.ScheduleVersion;
import com.github.line.schedulereadonlyapi.hateoas.ScheduleVersionAssembler;
import com.github.line.schedulereadonlyapi.repository.readonly.ScheduleVersionRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

public class ScheduleVersionService {
    private final ScheduleVersionRepository scheduleVersionRepository;
    private final ScheduleVersionAssembler scheduleVersionAssembler;

    private ScheduleVersionService() {
        throw new AssertionError();
    }

    public ScheduleVersionService(ScheduleVersionRepository scheduleVersionRepository, ScheduleVersionAssembler scheduleVersionAssembler) {
        this.scheduleVersionRepository = scheduleVersionRepository;
        this.scheduleVersionAssembler = scheduleVersionAssembler;
    }

    public CollectionModel<EntityModel<ScheduleVersion>> all() {
        return scheduleVersionAssembler.toCollectionModel(scheduleVersionRepository.findAll());
    }

    public EntityModel<ScheduleVersion> latest() {
        return one(scheduleVersionRepository.count());
    }

    public EntityModel<ScheduleVersion> one(Long scheduleVersionId) {
        return scheduleVersionAssembler.toModel(scheduleVersionRepository.getOne(scheduleVersionId));
    }
}
