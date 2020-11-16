package com.github.line.schedulereadonlyapi.controller;

import com.github.line.schedulereadonlyapi.domain.ClassObject;
import com.github.line.schedulereadonlyapi.service.ClassObjectService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClassObjectController {
    private final ClassObjectService classObjectService;

    private ClassObjectController() {
        throw new AssertionError();
    }

    public ClassObjectController(ClassObjectService classObjectService) {
        this.classObjectService = classObjectService;
    }

    @GetMapping(value = "/class-objects")
    public CollectionModel<EntityModel<ClassObject>> all() {
        return classObjectService.all();
    }

    @GetMapping(value = "/class-objects/{id}")
    public EntityModel<ClassObject> one(@PathVariable Long id) {
        return classObjectService.one(id);
    }
}
