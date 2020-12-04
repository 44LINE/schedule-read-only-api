package com.github.line.schedulereadonlyapi.service;

import com.github.line.schedulereadonlyapi.hateoas.ClassObjectAssembler;
import com.github.line.schedulereadonlyapi.repository.readonly.ClassObjectRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClassObjectServiceTest {

    @Mock
    private ClassObjectRepository repository;

    @Mock
    private ClassObjectAssembler assembler;

    private ClassObjectService service;
    private final Long ID = 1L;

    @BeforeEach
    void setUp() {
        service = new ClassObjectService(repository, assembler);
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