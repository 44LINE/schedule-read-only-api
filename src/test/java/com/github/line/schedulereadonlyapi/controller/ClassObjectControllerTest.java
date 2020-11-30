package com.github.line.schedulereadonlyapi.controller;

import com.github.line.schedulereadonlyapi.domain.ClassObject;
import com.github.line.schedulereadonlyapi.hateoas.ClassObjectAssembler;
import com.github.line.schedulereadonlyapi.service.ClassObjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@RunWith(SpringRunner.class)
//@WebMvcTest
@SpringBootTest
@EnableAutoConfiguration
class ClassObjectControllerTest {

    //@Autowired
    private MockMvc mockMvc;

    private ClassObjectAssembler assembler;

    @MockBean
    private ClassObjectService service;

    private static String BASE_PATH = "http://localhost";
    private static String DEFAULT_PORT = ":8080";
    private static String CLASS_OBJECTS_PATH = "/class-objects";
    private static final long ID = 1;
    private ClassObject classObject;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new ClassObjectController(service))
                .build();
        assembler = new ClassObjectAssembler();
        setUpClassObject();
    }

    private void setUpClassObject() {
        classObject = new ClassObject();
        classObject.setId(ID);
        classObject.setName("Class Object");
        classObject.setShortName("CO");
    }

    @Test
    public void testOne() throws Exception {
        given(service.one(ID))
                .willReturn(assembler.toModel(classObject));

        final ResultActions result = mockMvc.perform(get(BASE_PATH + DEFAULT_PORT + CLASS_OBJECTS_PATH + "/" + ID));
        result.andExpect(status().isOk());
        verifyJson(result);
    }

    private void verifyJson(final ResultActions action) throws Exception {
        action
                .andExpect(jsonPath("id", is(classObject.getId().intValue())))
                .andExpect(jsonPath("name", is(classObject.getName())))
                .andExpect(jsonPath("shortName", is(classObject.getShortName())))
                .andExpect(jsonPath("links[0].href", is(BASE_PATH + CLASS_OBJECTS_PATH + "/" + ID)))
                .andExpect(jsonPath("links[0].rel", is("self")))
                .andExpect(jsonPath("links[1].href", is(BASE_PATH + CLASS_OBJECTS_PATH)))
                .andExpect(jsonPath("links[1].rel", is("class-objects")));
    }
}