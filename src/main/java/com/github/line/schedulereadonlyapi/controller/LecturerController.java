package com.github.line.schedulereadonlyapi.controller;

import com.github.line.schedulereadonlyapi.domain.Lecturer;
import com.github.line.schedulereadonlyapi.service.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LecturerController {
    private final LecturerService lecturerService;

    public LecturerController(@Autowired LecturerService lecturerService) {
        this.lecturerService = lecturerService;
    }

    @GetMapping(value = "/lecturers")
    public CollectionModel<EntityModel<Lecturer>> all() {
        return lecturerService.all();
    }

    @GetMapping(value = "/lecturers/{id}")
    public EntityModel<Lecturer> one(@PathVariable Long id) {
        return lecturerService.one(id);
    }
}
