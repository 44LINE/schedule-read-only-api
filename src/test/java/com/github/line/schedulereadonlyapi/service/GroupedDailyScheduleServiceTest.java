package com.github.line.schedulereadonlyapi.service;

import com.github.line.schedulereadonlyapi.hateoas.GroupedDailyScheduleAssembler;
import com.github.line.schedulereadonlyapi.repository.readonly.GroupedDailyScheduleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GroupedDailyScheduleServiceTest {

    @Mock
    private GroupedDailyScheduleRepository repository;

    @Mock
    private GroupedDailyScheduleAssembler assembler;

    private GroupedDailyScheduleService service;
    private final Long ID = 1L;

    @BeforeEach
    void setUp() {
        service = new GroupedDailyScheduleService(repository, assembler);
    }

    @AfterEach
    void tearDown() {
        clearInvocations(repository);
    }

    @Test
    void testOne() {
        service.one(ID);
        verify(repository, times(1)).getOne(any());
    }

    @Test
    void testAll() {
        service.allByScheduleId(ID);
        verify(repository, times(1)).findByScheduleId(any());
    }

    @Test
    void testAllLatestByGroupId() {
        service.allLatestByGroupIdSorted(ID);
        verify(repository, times(1)).findLatestByGroupChronologically(any());
    }

    @Test
    void testLatest() {
        service.allLatest();
        verify(repository, times(1)).findByLatestSchedule();
    }

    @Test
    void testAllByGroupId() {
        service.allByScheduleIdAndGroupIdSorted(ID, ID);
        verify(repository, times(1)).findByScheduleAndGroupChronologically(any(), any());
    }
}