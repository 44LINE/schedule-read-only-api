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
    private final LecturerRepository lecturerRepository;
    private final RepresentationModelAssembler<Lecturer, EntityModel<Lecturer>> lecturerAssembler;

    public LecturerService(@Autowired LecturerRepository lecturerRepository,
                           @Autowired RepresentationModelAssembler<Lecturer, EntityModel<Lecturer>> lecturerAssembler) {
        this.lecturerRepository = lecturerRepository;
        this.lecturerAssembler = lecturerAssembler;
    }

    public CollectionModel<EntityModel<Lecturer>> all() {
        return lecturerAssembler.toCollectionModel(lecturerRepository.findAll());
    }

    public EntityModel<Lecturer> one(Long lecturerId) {
        return lecturerAssembler.toModel(lecturerRepository.findById(lecturerId).get());
    }
}
