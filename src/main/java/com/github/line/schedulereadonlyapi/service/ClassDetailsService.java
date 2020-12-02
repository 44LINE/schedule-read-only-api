package com.github.line.schedulereadonlyapi.service;

import com.github.line.schedulereadonlyapi.domain.ClassDetails;
import com.github.line.schedulereadonlyapi.repository.readonly.ClassDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Service;

@Service
public class ClassDetailsService {
    private final ClassDetailsRepository classDetailsRepository;
    private final RepresentationModelAssembler<ClassDetails, EntityModel<ClassDetails>> classDetailsAssembler;

    public ClassDetailsService(@Autowired ClassDetailsRepository classDetailsRepository,
                               @Autowired RepresentationModelAssembler<ClassDetails, EntityModel<ClassDetails>> classDetailsAssembler) {
        this.classDetailsRepository = classDetailsRepository;
        this.classDetailsAssembler = classDetailsAssembler;
    }

    public EntityModel<ClassDetails> one(Long scheduleId, Long groupedDailyScheduleId, Long classDetailsId) {
        return classDetailsAssembler.toModel(
                classDetailsRepository.getOne(classDetailsId));
    }

    public CollectionModel<EntityModel<ClassDetails>> all(Long scheduleId, Long groupedDailyScheduleId) {
        return classDetailsAssembler.toCollectionModel(
                classDetailsRepository.findByGroupedDailySchedule(groupedDailyScheduleId));
    }
}

