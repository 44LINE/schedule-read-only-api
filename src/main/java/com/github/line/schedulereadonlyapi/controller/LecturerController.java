package com.github.line.schedulereadonlyapi.controller;

import com.github.line.schedulereadonlyapi.domain.api.Lecturer;
import com.github.line.schedulereadonlyapi.service.LecturerService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lecturers")
public class LecturerController {
    private final LecturerService service;

    @GetMapping
    public CollectionModel<EntityModel<Lecturer>> all() {
        return service.all();
    }

    @GetMapping(value = "/{id}")
    public EntityModel<Lecturer> one(@PathVariable Long id) {
        return service.one(id);
    }
}
