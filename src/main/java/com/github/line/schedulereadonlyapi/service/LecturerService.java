package com.github.line.schedulereadonlyapi.service;

import com.github.line.schedulereadonlyapi.domain.api.Lecturer;
import com.github.line.schedulereadonlyapi.repository.readonly.LecturerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LecturerService {
    private final LecturerRepository repository;
    private final RepresentationModelAssembler<Lecturer, EntityModel<Lecturer>> assembler;

    public EntityModel<Lecturer> one(Long lecturerId) {
        return assembler.toModel(repository.getOne(lecturerId));
    }

    public CollectionModel<EntityModel<Lecturer>> all() {
        return assembler.toCollectionModel(repository.findAll());
    }
}
