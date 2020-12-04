package com.github.line.schedulereadonlyapi.controller;

import com.github.line.schedulereadonlyapi.domain.ClassObject;
import com.github.line.schedulereadonlyapi.service.ClassObjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ClassObjectController {
    private final ClassObjectService service;

    @GetMapping(value = "/class-objects")
    public CollectionModel<EntityModel<ClassObject>> all() {
        return service.all();
    }

    @GetMapping(value = "/class-objects/{id}")
    public EntityModel<ClassObject> one(@PathVariable Long id) {
        return service.one(id);
    }
}
