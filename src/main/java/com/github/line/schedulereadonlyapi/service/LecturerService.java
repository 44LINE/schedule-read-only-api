package com.github.line.schedulereadonlyapi.service;

import com.github.line.schedulereadonlyapi.domain.Lecturer;
import com.github.line.schedulereadonlyapi.repository.readonly.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Service;

@Service
public class LecturerService {
    private final LecturerRepository repository;
    private final RepresentationModelAssembler<Lecturer, EntityModel<Lecturer>> assembler;

    public LecturerService(@Autowired LecturerRepository repository,
                           @Autowired RepresentationModelAssembler<Lecturer, EntityModel<Lecturer>> assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    public EntityModel<Lecturer> one(Long lecturerId) {
        return assembler.toModel(repository.getOne(lecturerId));
    }

    public CollectionModel<EntityModel<Lecturer>> all() {
        return assembler.toCollectionModel(repository.findAll());
    }
}
