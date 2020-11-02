package com.github.line.schedulereadonlyapi.service;

import com.github.line.schedulereadonlyapi.domain.ClassObject;
import com.github.line.schedulereadonlyapi.hateoas.ClassObjectAssembler;
import com.github.line.schedulereadonlyapi.repository.readonly.ClassObjectRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

public class ClassObjectService {
    private final ClassObjectRepository classObjectRepository;
    private final ClassObjectAssembler classObjectAssembler;

    private ClassObjectService() {
        throw new AssertionError();
    }

    public ClassObjectService(ClassObjectRepository classObjectRepository, ClassObjectAssembler classObjectAssembler) {
        this.classObjectRepository = classObjectRepository;
        this.classObjectAssembler = classObjectAssembler;
    }

    public CollectionModel<EntityModel<ClassObject>> all() {
        return classObjectAssembler.toCollectionModel(classObjectRepository.findAll());
    }

    public EntityModel<ClassObject> one(Long classObjectId) {
        return classObjectAssembler.toModel(classObjectRepository.findById(classObjectId).get());
    }
}
