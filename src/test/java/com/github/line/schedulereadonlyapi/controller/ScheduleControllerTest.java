package com.github.line.schedulereadonlyapi.controller;

import com.github.line.schedulereadonlyapi.domain.Schedule;
import com.github.line.schedulereadonlyapi.hateoas.ScheduleAssembler;
import com.github.line.schedulereadonlyapi.service.ScheduleService;
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
@WebMvcTest(ScheduleController.class)
class ScheduleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ScheduleService service;

    private ScheduleAssembler assembler;
    private static String BASE_PATH = "http://localhost";
    private static String DEFAULT_PORT = ":8080";
    private static String SCHEDULES_PATH = "/schedules";
    private static String GROUPED_DAILY_SCHEDULES_PATH = "/grouped-daily-schedules";
    private static String LATEST = "/latest";
    private static final long ID = 1;
    private Schedule schedule;

    @BeforeEach
    public void setup() {
        assembler = new ScheduleAssembler();
        setUpSchedule();
    }

    private void setUpSchedule() {
        schedule = new Schedule();
        schedule.setId(ID);
        schedule.setLatest(true);
    }

    @Test
    public void testOne() throws Exception {
        given(service.one(ID)).willReturn(assembler.toModel(schedule));
        final ResultActions result = mockMvc.perform(get(BASE_PATH + DEFAULT_PORT + SCHEDULES_PATH + "/" + ID));
        result.andExpect(status().isOk());
        verifyJson(result);
    }

    @Test
    public void testLatest() throws Exception {
        given(service.latest()).willReturn(assembler.toModel(schedule));
        final ResultActions result = mockMvc.perform(get(BASE_PATH + DEFAULT_PORT + SCHEDULES_PATH + LATEST));
        result.andExpect(status().isOk());
        verifyJson(result);
    }

    private void verifyJson(final ResultActions action) throws Exception {
        action
                .andExpect(jsonPath("id", is(schedule.getId().intValue()) ))
                .andExpect(jsonPath("latest", is(schedule.isLatest()) ))
                .andExpect(jsonPath("_links.self.href", is(SCHEDULES_PATH + "/" + ID) ))
                .andExpect(jsonPath("_links.groupedDailyScheduleList.href", is(
                        SCHEDULES_PATH + "/" + ID + GROUPED_DAILY_SCHEDULES_PATH) ))
                .andDo(print());
    }

    @Test
    public void testAll() throws Exception {
        given(service.all())
                .willReturn(assembler.toCollectionModel(Collections.singleton(schedule)));
        final ResultActions result = mockMvc.perform(get(BASE_PATH + DEFAULT_PORT + SCHEDULES_PATH));
        result.andExpect(status().isOk());
        verifyEmbeddedJson(result);
    }

    private void verifyEmbeddedJson(final ResultActions action) throws Exception {
        action
                .andExpect(jsonPath("_embedded.scheduleList[0].id", is(
                        schedule.getId().intValue()) ))
                .andExpect(jsonPath("_embedded.scheduleList[0].latest", is(
                        schedule.isLatest()) ))
                .andExpect(jsonPath("_embedded.scheduleList[0]._links.self.href", is(
                        SCHEDULES_PATH + "/" + schedule.getId()) ))
                .andExpect(jsonPath("_embedded.scheduleList[0]._links.groupedDailyScheduleList.href", is(
                        SCHEDULES_PATH + "/" + schedule.getId() + GROUPED_DAILY_SCHEDULES_PATH) ))
                .andExpect(jsonPath("_links.self.href", is(SCHEDULES_PATH) ))
                .andDo(print());
    }

}