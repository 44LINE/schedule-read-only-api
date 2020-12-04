package com.github.line.schedulereadonlyapi.controller;

import com.github.line.schedulereadonlyapi.domain.GroupedDailySchedule;
import com.github.line.schedulereadonlyapi.domain.Schedule;
import com.github.line.schedulereadonlyapi.hateoas.GroupedDailyScheduleAssembler;
import com.github.line.schedulereadonlyapi.service.GroupedDailyScheduleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.util.Collections;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(GroupedDailyScheduleController.class)
class GroupedDailyScheduleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GroupedDailyScheduleService service;

    private GroupedDailyScheduleAssembler assembler;
    private static final String BASE_PATH = "http://localhost";
    private static final String DEFAULT_PORT = ":8080";
    private static final String SCHEDULES_PATH = "/schedules";
    private static final String GROUPED_DAILY_SCHEDULES_PATH = "/grouped-daily-schedules";
    private static final String CLASS_DETAILS_PATH = "/class-details";
    private static final String GROUP = "/group-id/";
    private static final String LATEST = "/latest";
    private static final long ID = 1;
    private GroupedDailySchedule groupedDailySchedule;

    @BeforeEach
    void setUp() {
        assembler = new GroupedDailyScheduleAssembler();
        setUpGroupedDailySchedule();
    }

    private void setUpGroupedDailySchedule() {
        groupedDailySchedule = new GroupedDailySchedule();

        Schedule schedule = new Schedule();
        schedule.setId(ID);

        groupedDailySchedule.setId(ID);
        groupedDailySchedule.setSchedule(schedule);
        groupedDailySchedule.setGroupId(ID);
        groupedDailySchedule.setDate(LocalDate.now());
    }

    @Test
    void testOne() throws Exception {
        given(service.one(ID)).willReturn(assembler.toModel(groupedDailySchedule));
        final ResultActions result = mockMvc.perform(get(
                BASE_PATH + DEFAULT_PORT + GROUPED_DAILY_SCHEDULES_PATH + "/" + ID));
        verifyJson(result);
    }

    private void verifyJson(ResultActions action) throws Exception {
        action
                .andExpect(jsonPath("id", is(
                        groupedDailySchedule.getId().intValue()) ))
                .andExpect(jsonPath("groupId", is(
                        groupedDailySchedule.getGroupId().intValue()) ))
                .andExpect(jsonPath("date", is(
                        groupedDailySchedule.getDate().toString()) ))
                .andExpect(jsonPath("_links.self.href", is(
                        GROUPED_DAILY_SCHEDULES_PATH + "/" + groupedDailySchedule.getId()) ))
                .andExpect(jsonPath("_links.classDetailsList.href", is(
                        GROUPED_DAILY_SCHEDULES_PATH + "/" + groupedDailySchedule.getId() + CLASS_DETAILS_PATH) ))
                .andDo(print());
    }

    @Test
    void testAllByScheduleId() throws Exception {
        given(service.allByScheduleId(ID)).willReturn(assembler.toCollectionModel(Collections.singleton(groupedDailySchedule)));
        final ResultActions result = mockMvc.perform(get(
                BASE_PATH + DEFAULT_PORT + SCHEDULES_PATH + "/" + ID + GROUPED_DAILY_SCHEDULES_PATH));
        verifyEmbeddedJson(result);
    }

    @Test
    void testAllLatest() throws Exception {
        given(service.allLatest()).willReturn(assembler.toCollectionModel(Collections.singleton(groupedDailySchedule)));
        final ResultActions result = mockMvc.perform(get(
                BASE_PATH + DEFAULT_PORT + SCHEDULES_PATH + LATEST + GROUPED_DAILY_SCHEDULES_PATH));
        verifyEmbeddedJson(result);
    }

    @Test
    void testAllByScheduleIdAndGroupIdSorted() throws Exception {
        given(service.allByScheduleIdAndGroupIdSorted(ID, ID -1)).willReturn(assembler.toCollectionModel(Collections.singleton(groupedDailySchedule)));
        final ResultActions result = mockMvc.perform(get(
                BASE_PATH + DEFAULT_PORT + SCHEDULES_PATH + "/" + ID + GROUPED_DAILY_SCHEDULES_PATH + GROUP + ID));
        verifyEmbeddedJson(result);
    }

    @Test
    void testAllLatestByGroupIdSorted() throws Exception {
        given(service.allLatestByGroupIdSorted(ID -1)).willReturn(assembler.toCollectionModel(Collections.singleton(groupedDailySchedule)));
        final ResultActions result = mockMvc.perform(get(
                BASE_PATH + DEFAULT_PORT + SCHEDULES_PATH + LATEST + GROUPED_DAILY_SCHEDULES_PATH + GROUP + ID));
        verifyEmbeddedJson(result);
    }

    private void verifyEmbeddedJson(ResultActions action) throws Exception {
        action
                .andExpect(jsonPath("_embedded.groupedDailyScheduleList[0].id", is(
                        groupedDailySchedule.getId().intValue()) ))
                .andExpect(jsonPath("_embedded.groupedDailyScheduleList[0].groupId", is(
                        groupedDailySchedule.getGroupId().intValue()) ))
                .andExpect(jsonPath("_embedded.groupedDailyScheduleList[0].date", is(
                        groupedDailySchedule.getDate().toString()) ))
                .andExpect(jsonPath("_embedded.groupedDailyScheduleList[0]._links.self.href", is(
                        GROUPED_DAILY_SCHEDULES_PATH + "/" + groupedDailySchedule.getId()) ))
                .andExpect(jsonPath("_embedded.groupedDailyScheduleList[0]._links.classDetailsList.href", is(
                        GROUPED_DAILY_SCHEDULES_PATH + "/" + groupedDailySchedule.getId() + CLASS_DETAILS_PATH) ))
                .andExpect(jsonPath("_links.self.href", is(
                        SCHEDULES_PATH + "/" + groupedDailySchedule.getSchedule().getId() + GROUPED_DAILY_SCHEDULES_PATH
                ) ))
                .andDo(print());
    }
}
