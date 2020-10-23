package com.github.line.schedulereadonlyapi.service;

import com.github.line.schedulereadonlyapi.domain.ClassDetails;
import com.github.line.schedulereadonlyapi.hateoas.ClassDetailsAssembler;
import com.github.line.schedulereadonlyapi.repository.readonly.ClassDetailsRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

public class ClassDetailsService {
    private final ClassDetailsRepository classDetailsRepository;
    private final ClassDetailsAssembler classDetailsAssembler;

    private ClassDetailsService() {
        throw new AssertionError();
    }

    public ClassDetailsService(ClassDetailsRepository classDetailsRepository, ClassDetailsAssembler classDetailsAssembler) {
        this.classDetailsRepository = classDetailsRepository;
        this.classDetailsAssembler = classDetailsAssembler;
    }

    public EntityModel<ClassDetails> one(Long scheduleId, Long groupedDailyScheduleId, Long classDetailsId) {
        return classDetailsAssembler.toModel(null);
    }

    public CollectionModel<EntityModel<ClassDetails>> all(Long scheduleId, Long groupedDailyScheduleId) {
        return classDetailsAssembler.toCollectionModel(null);
    }
}

