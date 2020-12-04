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
    private final ClassObjectRepository repository;
    private final RepresentationModelAssembler<ClassObject, EntityModel<ClassObject>> assembler;

    public ClassObjectService(@Autowired ClassObjectRepository repository,
                              @Autowired RepresentationModelAssembler<ClassObject, EntityModel<ClassObject>> assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    public EntityModel<ClassObject> one(Long classObjectId) {
        return assembler.toModel(repository.getOne(classObjectId));
    }

    public CollectionModel<EntityModel<ClassObject>> all() {
        return assembler.toCollectionModel(repository.findAll());
    }
}
