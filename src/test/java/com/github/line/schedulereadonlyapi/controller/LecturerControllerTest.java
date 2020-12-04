package com.github.line.schedulereadonlyapi.controller;

import com.github.line.schedulereadonlyapi.domain.Lecturer;
import com.github.line.schedulereadonlyapi.hateoas.LecturerAssembler;
import com.github.line.schedulereadonlyapi.service.LecturerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
@WebMvcTest(LecturerController.class)
class LecturerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LecturerService service;

    private LecturerAssembler assembler;
    private static String BASE_PATH = "http://localhost";
    private static String DEFAULT_PORT = ":8080";
    private static String LECTURER_PATH = "/lecturers";
    private static final long ID = 1;
    private Lecturer lecturer;

    @BeforeEach
    public void setup() {
        assembler = new LecturerAssembler();
        setUpLecturer();
    }

    private void setUpLecturer() {
        lecturer = new Lecturer();
        lecturer.setId(ID);
        lecturer.setName("Name");
        lecturer.setSurname("Surname");
        lecturer.setShortName("LEC");
        lecturer.setEmail("sample@sample");
    }

    @Test
    public void testOne() throws Exception {
        given(service.one(ID)).willReturn(assembler.toModel(lecturer));
        final ResultActions result = mockMvc.perform(get(BASE_PATH + DEFAULT_PORT + LECTURER_PATH + "/" + ID));
        result.andExpect(status().isOk());
        verifyJson(result);
    }

    private void verifyJson(final ResultActions action) throws Exception {
        action
                .andExpect(jsonPath("id", is(lecturer.getId().intValue()) ))
                .andExpect(jsonPath("name", is(lecturer.getName()) ))
                .andExpect(jsonPath("surname", is(lecturer.getSurname()) ))
                .andExpect(jsonPath("shortName", is(lecturer.getShortName()) ))
                .andExpect(jsonPath("_links.self.href", is(LECTURER_PATH + "/" + ID) ))
                .andDo(print());
    }

    @Test
    public void testAll() throws Exception {
        given(service.all())
                .willReturn(assembler.toCollectionModel(Collections.singleton(lecturer)));
        final ResultActions result = mockMvc.perform(get(BASE_PATH + DEFAULT_PORT + LECTURER_PATH));
        result.andExpect(status().isOk());
        result
                .andExpect(jsonPath("_embedded.lecturerList[0].id", is(lecturer.getId().intValue()) ))
                .andExpect(jsonPath("_embedded.lecturerList[0].name", is(lecturer.getName()) ))
                .andExpect(jsonPath("_embedded.lecturerList[0].surname", is(lecturer.getSurname()) ))
                .andExpect(jsonPath("_embedded.lecturerList[0].shortName", is(lecturer.getShortName()) ))
                .andExpect(jsonPath("_embedded.lecturerList[0]._links.self.href", is(
                        LECTURER_PATH + "/" + lecturer.getId().intValue()) ));
    }
}