package com.github.line.schedulereadonlyapi.controller;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Links;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class MainController {

    @GetMapping(value = "/")
    public Links mainEndpoint() {
        return Links.of(
                linkTo(methodOn(ScheduleVersionController.class).all()).withRel("schedule-versions"),
                linkTo(methodOn(ScheduleController.class).all()).withRel("schedules"),
                linkTo(methodOn(LecturerController.class).all()).withRel("lecturers"),
                linkTo(methodOn(ClassObjectController.class).all()).withRel("class-objects"),
                Link.of("https://44line.github.io/", "author")
        );
    }
}
