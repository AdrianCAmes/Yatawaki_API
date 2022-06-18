package com.orchestrator.orchestrator.business.impl;

import com.orchestrator.orchestrator.business.ComposerService;
import com.orchestrator.orchestrator.model.Composer;
import com.orchestrator.orchestrator.repository.ComposerRepository;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import com.orchestrator.orchestrator.utils.constants.ComposerStatusConstants;
import com.orchestrator.orchestrator.utils.impl.GeneralUtilsImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ComposerServiceImplTest {

    @Mock
    private ComposerRepository composerRepository;

    private GeneralUtils generalUtils;
    private ComposerService composerService;

    @Mock
    private ComposerService composerServiceMock = mock(ComposerServiceImpl.class);

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        generalUtils = new GeneralUtilsImpl();
        composerService = new ComposerServiceImpl(composerRepository, generalUtils) {
        };
    }

    @Test
    @DisplayName("When create With Valid Attributes And Without An Id Then Returns Composer")
    void create() {
        Composer composer = new Composer();
        composer.setName("My name");

        Composer createdComposer = new Composer();
        createdComposer.setIdComposer(1L);

        when(composerRepository.save(any())).thenReturn(createdComposer);

        Composer result = composerService.create(composer);

        assertEquals(result.getIdComposer(), createdComposer.getIdComposer());
    }

    @Test
    @DisplayName("When create With Valid Attributes And With An Id Then Returns IllegalArgumentException")
    void createIllegalArgumentException() throws  IllegalArgumentException{
        String expectedMessage = "Body should not contain id";
        Composer composer = new Composer();
        composer.setName("My name");
        composer.setIdComposer(1L);

        Throwable exception = Assertions.catchThrowable( () -> {
            Composer result = composerService.create(composer);
        });

        Assertions.assertThat(exception).isInstanceOf(IllegalArgumentException.class).hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When findById With Valid Id Then Returns Composer")
    void findById() {
        Composer composer = new Composer();
        composer.setName("My name");

        when(composerRepository.findById(1L)).thenReturn(Optional.ofNullable(composer));

        final Composer result = composerService.findById(1L);

        assertEquals(result.toString(), composer.toString());
    }

    @Test
    @DisplayName("When findById With Invalid Id Then Returns Null")
    void findByIdNull() {
        assertNull(composerService.findById(1L));
    }


    @Test
    @DisplayName("When findAll Then Returns Composer List")
    void findAll() {
        Composer composer = new Composer();
        composer.setName("My name");

        Composer composer2 = new Composer();
        composer2.setName("My name 2");

        List<Composer> composerList = new ArrayList<>();
        composerList.add(composer);
        composerList.add(composer2);

        when(composerRepository.findAll()).thenReturn( composerList);

        List<Composer> result = composerService.findAll();

        assertEquals(result.size(), 2);
    }

    @Test
    @DisplayName("When update With Valid Attributes And Valid Id Then Returns Composer")
    void update() throws IllegalAccessException {
        Composer composer = new Composer();
        composer.setName("New Name");

        Composer retrievedComposer = new Composer();
        retrievedComposer.setIdComposer(4L);
        retrievedComposer.setName("Old Name");

        when(composerRepository.findById(any())).thenReturn(Optional.of(retrievedComposer));
        when(composerRepository.save(any())).thenReturn(retrievedComposer);

        final Composer result = composerService.update(composer);

        assertEquals(retrievedComposer.toString(), result.toString());
    }

    @Test
    @DisplayName("When update With Valid Attributes And Invalid Id Then Returns NoSuchElementException")
    void updateNoSuchElementException() throws IllegalAccessException {
        String expectedMessage = "Element does not exist in database";
        Composer retrievedComposer = new Composer();
        retrievedComposer.setIdComposer(4L);
        retrievedComposer.setName("Name");

        Throwable exception = Assertions.catchThrowable( () -> {
            Composer result = composerService.update(retrievedComposer);
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }



    @Test
    @DisplayName("When change With Valid Attributes And Valid Id Then Returns Composer")
    void change() throws IllegalAccessException {
        Composer composer = new Composer();
        composer.setName("New Name");

        Composer retrievedComposer = new Composer();
        retrievedComposer.setIdComposer(4L);
        retrievedComposer.setName("Old Name");

        when(composerRepository.findById(any())).thenReturn(Optional.of(retrievedComposer));
        when(composerRepository.save(any())).thenReturn(retrievedComposer);

        final Composer result = composerService.change(composer);

        assertEquals(retrievedComposer.toString(), result.toString());
    }

    @Test
    @DisplayName("When change With Valid Attributes And Invalid Id Then Returns NoSuchElementException")
    void changeNoSuchElementException() throws IllegalAccessException {
        String expectedMessage = "Element does not exist in database";
        Composer retrievedComposer = new Composer();
        retrievedComposer.setIdComposer(4L);
        retrievedComposer.setName("Name");

        Throwable exception = Assertions.catchThrowable( () -> {
            Composer result = composerService.change(retrievedComposer);
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When removeById With Valid Id Then Returns Composer")
    void removeById(){
        Composer retrievedComposer = new Composer();
        retrievedComposer.setIdComposer(4L);
        retrievedComposer.setName("Name");

        when(composerRepository.findById(4L)).thenReturn(Optional.of(retrievedComposer));

        composerService.removeById(4L);
        verify(composerRepository).deleteById(4L);
    }


    @Test
    @DisplayName("When removeById Invalid Id Then Returns NoSuchElementException")
    void removeByIdNoSuchElementException() {
        String expectedMessage = "Element does not exist in database";

        Throwable exception = Assertions.catchThrowable( () -> {
            Composer result = composerService.removeById(4L);
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When findAll Then Returns ComposerStatusConstants List")
    void getPossibleStatus() {
        ComposerStatusConstants composerStatusConstants = ComposerStatusConstants.DELETED;
        ComposerStatusConstants composerStatusConstants1 = ComposerStatusConstants.ACTIVE;

        List<ComposerStatusConstants> composerStatusConstantsList = new ArrayList<>();
        composerStatusConstantsList.add(composerStatusConstants);
        composerStatusConstantsList.add(composerStatusConstants1);

        when(composerServiceMock.getPossibleStatus()).thenReturn(Arrays.stream(ComposerStatusConstants.values()).collect(Collectors.toList()));

        List<ComposerStatusConstants> result = composerService.getPossibleStatus();

        assertEquals(result, composerStatusConstantsList);
    }
}
