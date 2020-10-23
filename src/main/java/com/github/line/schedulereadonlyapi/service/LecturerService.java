package com.github.line.schedulereadonlyapi.service;

import com.github.line.schedulereadonlyapi.domain.Lecturer;
import com.github.line.schedulereadonlyapi.hateoas.LecturerAssembler;
import com.github.line.schedulereadonlyapi.repository.readonly.LecturerRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

public class LecturerService {
    private final LecturerRepository lecturerRepository;
    private final LecturerAssembler lecturerAssembler;

    private LecturerService() {
        throw new AssertionError();
    }

    public LecturerService(LecturerRepository lecturerRepository, LecturerAssembler lecturerAssembler) {
        this.lecturerRepository = lecturerRepository;
        this.lecturerAssembler = lecturerAssembler;
    }

    public CollectionModel<EntityModel<Lecturer>> all() {
        return lecturerAssembler.toCollectionModel(lecturerRepository.findAll());
    }

    public EntityModel<Lecturer> one(Long lecturerId) {
        return lecturerAssembler.toModel(lecturerRepository.getOne(lecturerId));
    }
}
