package com.github.line.schedulereadonlyapi.service;

import com.github.line.schedulereadonlyapi.hateoas.LecturerAssembler;
import com.github.line.schedulereadonlyapi.repository.readonly.LecturerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LecturerServiceTest {

    @Mock
    private LecturerRepository repository;

    @Mock
    private LecturerAssembler assembler;

    private LecturerService service;
    private final Long ID = 1L;

    @BeforeEach
    void setUp() {
        service = new LecturerService(repository, assembler);
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
        service.all();
        verify(repository, times(1)).findAll();
    }
}