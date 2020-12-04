package com.github.line.schedulereadonlyapi.service;

import com.github.line.schedulereadonlyapi.hateoas.ScheduleAssembler;
import com.github.line.schedulereadonlyapi.repository.readonly.ScheduleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ScheduleServiceTest {

    @Mock
    private ScheduleRepository repository;

    @Mock
    private ScheduleAssembler assembler;

    private ScheduleService service;
    private final Long ID = 1L;

    @BeforeEach
    void setUp() {
        service = new ScheduleService(repository, assembler);
    }

    @AfterEach
    void tearDown() {
        clearInvocations(repository);
    }

    @Test
    void testOne() {
        service.one(ID);
        verify(repository, times(1)).getOne(anyLong());
    }

    @Test
    void testAll() {
        service.all();
        verify(repository, times(1)).findAll();
    }

    @Test
    void testLatest() {
        service.latest();
        verify(repository, times(1)).findLatest();
    }
}