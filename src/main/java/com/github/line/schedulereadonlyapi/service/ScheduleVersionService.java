package com.github.line.schedulereadonlyapi.service;

import com.github.line.schedulereadonlyapi.domain.ScheduleVersion;
import com.github.line.schedulereadonlyapi.repository.readonly.ScheduleVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Service;

@Service
public class ScheduleVersionService {
    private final ScheduleVersionRepository scheduleVersionRepository;
    private final RepresentationModelAssembler<ScheduleVersion, EntityModel<ScheduleVersion>> scheduleVersionAssembler;

    public ScheduleVersionService(@Autowired ScheduleVersionRepository scheduleVersionRepository,
                                  @Autowired RepresentationModelAssembler<ScheduleVersion, EntityModel<ScheduleVersion>> scheduleVersionAssembler) {
        this.scheduleVersionRepository = scheduleVersionRepository;
        this.scheduleVersionAssembler = scheduleVersionAssembler;
    }

    public CollectionModel<EntityModel<ScheduleVersion>> all() {
        return scheduleVersionAssembler.toCollectionModel(scheduleVersionRepository.findAll());
    }

    public EntityModel<ScheduleVersion> latest() {
        return scheduleVersionAssembler.toModel(scheduleVersionRepository.findLatest());
    }

    public EntityModel<ScheduleVersion> one(Long scheduleVersionId) {
        return scheduleVersionAssembler.toModel(scheduleVersionRepository.findById(scheduleVersionId).get());
    }
}
