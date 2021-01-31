package com.github.line.schedulereadonlyapi.controller;

import com.github.line.schedulereadonlyapi.domain.api.*;
import com.github.line.schedulereadonlyapi.enums.ClassType;
import com.github.line.schedulereadonlyapi.hateoas.ClassDetailsAssembler;
import com.github.line.schedulereadonlyapi.service.ClassDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalTime;
import java.util.Collections;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ClassDetailsController.class)
class ClassDetailsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClassDetailsService service;

    private ClassDetailsAssembler assembler;
    private static String BASE_PATH = "http://localhost";
    private static String DEFAULT_PORT = ":8080";
    private static String CLASS_DETAILS_PATH = "/class-details";
    private static String CLASS_OBJECTS_PATH = "/class-objects";
    private static String LECTURER_PATH = "/lecturers";
    private static String GROUPED_DAILY_SCHEDULES_PATH = "/grouped-daily-schedules";
    private static final long ID = 1;
    private ClassDetails classDetails;

    @BeforeEach
    public void setup() {
        assembler = new ClassDetailsAssembler();
        setUpClassObject();
    }

    private void setUpClassObject() {
        ClassObject classObject = new ClassObject();
        classObject.setId(ID);
        Lecturer lecturer = new Lecturer();
        lecturer.setId(ID);
        LocalTime localTime = LocalTime.of(8, 0, 0);
        GroupedDailySchedule groupedDailySchedule = new GroupedDailySchedule();
        groupedDailySchedule.setId(ID);

        classDetails = new ClassDetails();
        classDetails.setId(ID);
        classDetails.setClassObject(classObject);
        classDetails.setClassPeriod(new ClassPeriod(localTime, localTime));
        classDetails.setLecturer(lecturer);
        classDetails.setType(ClassType.EXERCISE);
        classDetails.setGroupedDailySchedule(groupedDailySchedule);
    }

    //@Test
    public void testOne() throws Exception {
        given(service.one(ID))
                .willReturn(assembler.toModel(classDetails));
        final ResultActions result = mockMvc.perform(get(BASE_PATH + DEFAULT_PORT + CLASS_DETAILS_PATH + "/" + ID));
        result.andExpect(status().isOk());
        verifyJson(result);
    }

    private void verifyJson(final ResultActions action) throws Exception {
        action
                .andExpect(jsonPath("id", is(
                        classDetails.getId().intValue()) ))
                .andExpect(jsonPath("type", is(
                        classDetails.getType().name()) ))
                .andExpect(jsonPath("classPeriod.startTime", is(
                        classDetails.getClassPeriod().getStartTime().format(ClassPeriod.TIME_FORMATTER)) ))
                .andExpect(jsonPath("classPeriod.endTime", is(
                        classDetails.getClassPeriod().getEndTime().format(ClassPeriod.TIME_FORMATTER)) ))
                .andExpect(jsonPath("_links.self.href", is(
                        CLASS_DETAILS_PATH + "/" + classDetails.getId()) ))
                .andExpect(jsonPath("_links.classObject.href", is(
                        CLASS_OBJECTS_PATH + "/" + classDetails.getClassObject().getId()) ))
                .andExpect(jsonPath("_links.lecturer.href", is(
                        LECTURER_PATH + "/" + classDetails.getLecturer().getId()) ))
                .andDo(print());
    }

    //@Test
    public void testAllByGroupedDailyScheduleId() throws Exception {
        given(service.allByGroupedDailyScheduleId(ID))
                .willReturn(assembler.toCollectionModel(Collections.singleton(classDetails)));
        final ResultActions result = mockMvc.perform(get(
                BASE_PATH + DEFAULT_PORT + GROUPED_DAILY_SCHEDULES_PATH + "/" + ID + CLASS_DETAILS_PATH));
        result.andExpect(status().isOk());
        verifyEmbeddedJson(result);
        result
                .andExpect(jsonPath("_links.self.href", is(
                        GROUPED_DAILY_SCHEDULES_PATH + "/" + ID + CLASS_DETAILS_PATH) ));
    }

    private void verifyEmbeddedJson(final ResultActions action) throws Exception {
        action
                .andExpect(jsonPath("_embedded.classDetailsList[0].id", is(
                        classDetails.getId().intValue()) ))
                .andExpect(jsonPath("_embedded.classDetailsList[0].type", is(
                        classDetails.getType().name()) ))
                .andExpect(jsonPath("_embedded.classDetailsList[0].classPeriod.startTime", is(
                        classDetails.getClassPeriod().getStartTime().format(ClassPeriod.TIME_FORMATTER)) ))
                .andExpect(jsonPath("_embedded.classDetailsList[0].classPeriod.endTime", is(
                        classDetails.getClassPeriod().getEndTime().format(ClassPeriod.TIME_FORMATTER)) ))
                .andExpect(jsonPath("_embedded.classDetailsList[0]._links.self.href", is(
                        CLASS_DETAILS_PATH + "/" + classDetails.getId()) ))
                .andExpect(jsonPath("_embedded.classDetailsList[0]._links.classObject.href", is(
                        CLASS_OBJECTS_PATH + "/" + classDetails.getClassObject().getId()) ))
                .andExpect(jsonPath("_embedded.classDetailsList[0]._links.lecturer.href", is(
                        LECTURER_PATH + "/" + classDetails.getLecturer().getId()) ))
                .andDo(print());
    }
}