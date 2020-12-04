package com.github.line.schedulereadonlyapi.service;

import com.github.line.schedulereadonlyapi.domain.ClassDetails;
import com.github.line.schedulereadonlyapi.repository.readonly.ClassDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClassDetailsService {
    private final ClassDetailsRepository repository;
    private final RepresentationModelAssembler<ClassDetails, EntityModel<ClassDetails>> assembler;

    public EntityModel<ClassDetails> one(Long classDetailsId) {
        return assembler.toModel(repository.getOne(classDetailsId));
    }

    public CollectionModel<EntityModel<ClassDetails>> allByGroupedDailyScheduleId(Long groupedDailyScheduleId) {
        return assembler.toCollectionModel(repository.findByGroupedDailySchedule(groupedDailyScheduleId));
    }
}

