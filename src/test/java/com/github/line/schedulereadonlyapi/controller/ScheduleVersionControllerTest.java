package com.github.line.schedulereadonlyapi.controller;

import com.github.line.schedulereadonlyapi.domain.api.ScheduleVersion;
import com.github.line.schedulereadonlyapi.hateoas.ScheduleVersionAssembler;
import com.github.line.schedulereadonlyapi.service.ScheduleVersionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ScheduleVersionController.class)
class ScheduleVersionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ScheduleVersionService service;

    private ScheduleVersionAssembler assembler;
    private static String BASE_PATH = "http://localhost";
    private static String DEFAULT_PORT = ":8080";
    private static String SCHEDULE_VERSIONS_PATH = "/schedule-versions";
    private static String LATEST = "/latest";
    private static final long ID = 1;
    private ScheduleVersion scheduleVersion;

    @BeforeEach
    public void setup() {
        assembler = new ScheduleVersionAssembler();
        setUpScheduleVersion();
    }

    private void setUpScheduleVersion() {
        scheduleVersion = new ScheduleVersion();
        scheduleVersion.setId(ID);
        scheduleVersion.setAdditionDate(LocalDateTime.now());
        scheduleVersion.setUpdateDate(LocalDateTime.now());
        scheduleVersion.setUrl("url");
    }

    //@Test
    public void testOne() throws Exception {
        given(service.one(ID)).willReturn(assembler.toModel(scheduleVersion));
        final ResultActions result = mockMvc.perform(get(BASE_PATH + DEFAULT_PORT + SCHEDULE_VERSIONS_PATH + "/" + ID));
        result.andExpect(status().isOk());
        verifyJson(result);
    }

    //@Test
    public void testLatest() throws Exception {
        given(service.latest()).willReturn(assembler.toModel(scheduleVersion));
        final ResultActions result = mockMvc.perform(get(BASE_PATH + DEFAULT_PORT + SCHEDULE_VERSIONS_PATH + LATEST));
        result.andExpect(status().isOk());
        verifyJson(result);
    }

    private void verifyJson(final ResultActions action) throws Exception {
        action
                .andExpect(jsonPath("id", is(scheduleVersion.getId().intValue()) ))
                .andExpect(jsonPath("url", is(scheduleVersion.getUrl()) ))
                .andExpect(jsonPath("updateDate", is(scheduleVersion.getUpdateDate().toString()) ))
                .andExpect(jsonPath("additionDate", is(scheduleVersion.getAdditionDate().toString()) ))
                .andExpect(jsonPath("_links.self.href", is(SCHEDULE_VERSIONS_PATH + "/" + ID) ))
                .andDo(print());
    }

    //@Test
    public void testAll() throws Exception {
        given(service.all())
                .willReturn(assembler.toCollectionModel(Collections.singleton(scheduleVersion)));
        final ResultActions result = mockMvc.perform(get(BASE_PATH + DEFAULT_PORT + SCHEDULE_VERSIONS_PATH));
        result.andExpect(status().isOk());
        verifyEmbeddedJson(result);
    }

    private void verifyEmbeddedJson(final ResultActions action) throws Exception {
        action
                .andExpect(jsonPath("_embedded.scheduleVersionList[0].id", is(
                        scheduleVersion.getId().intValue()) ))
                .andExpect(jsonPath("_embedded.scheduleVersionList[0].url", is(
                        scheduleVersion.getUrl()) ))
                .andExpect(jsonPath("_embedded.scheduleVersionList[0].updateDate", is(
                        scheduleVersion.getUpdateDate().toString()) ))
                .andExpect(jsonPath("_embedded.scheduleVersionList[0].additionDate", is(
                        scheduleVersion.getAdditionDate().toString()) ))
                .andExpect(jsonPath("_embedded.scheduleVersionList[0]._links.self.href", is(
                        SCHEDULE_VERSIONS_PATH + "/" + scheduleVersion.getId()) ))
                .andExpect(jsonPath("_links.self.href", is(
                        SCHEDULE_VERSIONS_PATH) ))
                .andExpect(jsonPath("_links.latest.href", is(
                        SCHEDULE_VERSIONS_PATH + LATEST
                ) ))
                .andDo(print());
    }

}