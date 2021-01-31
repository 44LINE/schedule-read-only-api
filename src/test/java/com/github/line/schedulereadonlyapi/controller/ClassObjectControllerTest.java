package com.github.line.schedulereadonlyapi.controller;

import com.github.line.schedulereadonlyapi.domain.api.ClassObject;
import com.github.line.schedulereadonlyapi.exception.ClassObjectNotFoundException;
import com.github.line.schedulereadonlyapi.hateoas.ClassObjectAssembler;
import com.github.line.schedulereadonlyapi.service.ClassObjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Collections;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ClassObjectController.class)
class ClassObjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClassObjectService service;

    private ClassObjectAssembler assembler;
    private static String BASE_PATH = "http://localhost";
    private static String DEFAULT_PORT = ":8080";
    private static String CLASS_OBJECTS_PATH = "/class-objects";
    private static final long ID = 1;
    private ClassObject classObject;

    @BeforeEach
    public void setup() {
        assembler = new ClassObjectAssembler();
        setUpClassObject();
    }

    private void setUpClassObject() {
        classObject = new ClassObject();
        classObject.setId(ID);
        classObject.setName("Class Object");
        classObject.setShortName("CO");
    }

    //@Test
    public void testOne() throws Exception {
        given(service.one(ID)).willReturn(assembler.toModel(classObject));
        final ResultActions result = mockMvc.perform(get(BASE_PATH + DEFAULT_PORT + CLASS_OBJECTS_PATH + "/" + ID));
        result.andExpect(status().isOk());
        verifyJson(result);
    }

    private void verifyJson(final ResultActions action) throws Exception {
        action
                .andExpect(jsonPath("id", is(classObject.getId().intValue()) ))
                .andExpect(jsonPath("name", is(classObject.getName()) ))
                .andExpect(jsonPath("shortName", is(classObject.getShortName()) ))
                .andExpect(jsonPath("_links.self.href", is(CLASS_OBJECTS_PATH + "/" + ID) ))
                .andDo(print());
    }

    //@Test
    public void testAll() throws Exception {
        given(service.all())
                .willReturn(assembler.toCollectionModel(Collections.singleton(classObject)));
        final ResultActions result = mockMvc.perform(get(BASE_PATH + DEFAULT_PORT + CLASS_OBJECTS_PATH));
        result.andExpect(status().isOk());
        result
                .andExpect(jsonPath("_embedded.classObjectList[0].id", is(classObject.getId().intValue()) ))
                .andExpect(jsonPath("_embedded.classObjectList[0].name", is(classObject.getName()) ))
                .andExpect(jsonPath("_embedded.classObjectList[0].shortName", is(classObject.getShortName()) ))
                .andExpect(jsonPath("_embedded.classObjectList[0]._links.self.href", is(
                        CLASS_OBJECTS_PATH + "/" + classObject.getId().intValue()) ))
                .andExpect(jsonPath("_links.self.href", is(CLASS_OBJECTS_PATH) ));
    }

    //@Test
    public void getClassObjectThatDoesNotExistReturnsError() throws Exception {
        ClassObjectNotFoundException exception = new ClassObjectNotFoundException(ID);
        given(service.one(ID)).willThrow(exception);
        final ResultActions result = mockMvc.perform(get(BASE_PATH + CLASS_OBJECTS_PATH + "/" + ID));
        result.andExpect(status().isNotFound())
              .andExpect(jsonPath("detail", is(exception.getMessage()) ));
    }
}