package com.github.line.schedulereadonlyapi.service;

import com.github.line.schedulereadonlyapi.domain.api.ClassObject;
import com.github.line.schedulereadonlyapi.repository.readonly.ClassObjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClassObjectService {
    private final ClassObjectRepository repository;
    private final RepresentationModelAssembler<ClassObject, EntityModel<ClassObject>> assembler;

    public EntityModel<ClassObject> one(Long classObjectId) {
        return assembler.toModel(repository.getOne(classObjectId));
    }

    public CollectionModel<EntityModel<ClassObject>> all() {
        return assembler.toCollectionModel(repository.findAll());
    }
}
