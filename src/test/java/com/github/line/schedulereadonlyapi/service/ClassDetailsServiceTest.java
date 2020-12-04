package com.github.line.schedulereadonlyapi.service;

import com.github.line.schedulereadonlyapi.hateoas.ClassDetailsAssembler;
import com.github.line.schedulereadonlyapi.repository.readonly.ClassDetailsRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClassDetailsServiceTest {

    @Mock
    private ClassDetailsRepository repository;

    @Mock
    private ClassDetailsAssembler assembler;

    private ClassDetailsService service;
    private final Long ID = 1L;

    @BeforeEach
    void setUp() {
        service = new ClassDetailsService(repository, assembler);
    }

    @AfterEach
    void tearDown() {
        clearInvocations(repository);
    }

    @Test
    void testOne() {
        service.one(ID, ID, ID);
        verify(repository, times(1)).getOne(any());
    }

    @Test
    void testAll() {
        service.all(ID, ID);
        verify(repository, times(1)).findByGroupedDailySchedule(any());
    }
}
