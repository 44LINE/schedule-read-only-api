package com.github.line.schedulereadonlyapi.service;

import com.github.line.schedulereadonlyapi.domain.ClassObject;
import com.github.line.schedulereadonlyapi.repository.readonly.ClassObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Service;

@Service
public class ClassObjectService {
    private final ClassObjectRepository classObjectRepository;
    private final RepresentationModelAssembler<ClassObject, EntityModel<ClassObject>> classObjectAssembler;

    public ClassObjectService(@Autowired ClassObjectRepository classObjectRepository,
                              @Autowired RepresentationModelAssembler<ClassObject, EntityModel<ClassObject>> classObjectAssembler) {
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
