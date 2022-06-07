package com.orchestrator.orchestrator.business.impl;

import com.orchestrator.orchestrator.business.GestureService;
import com.orchestrator.orchestrator.model.Gesture;
import com.orchestrator.orchestrator.repository.GestureRepository;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import com.orchestrator.orchestrator.utils.constants.GestureStatusConstants;
import com.orchestrator.orchestrator.utils.impl.GeneralUtilsImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.assertj.core.api.Assertions;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class GestureServiceImplTest {

    @Mock
    private GestureRepository gestureRepository;

    private GeneralUtils generalUtils;
    private GestureService gestureService;

    @Mock
    private GestureService gestureServiceMock = mock(GestureServiceImpl.class);

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        generalUtils = new GeneralUtilsImpl();
        gestureService = new GestureServiceImpl(gestureRepository, generalUtils) {
        };
    }

    @Test
    @DisplayName("When create With Valid Attributes And Without An Id Then Returns Gesture")
    void create() {
        Gesture gesture = new Gesture();
        gesture.setStatus(1);

        Gesture createdGesture = new Gesture();
        createdGesture.setIdGesture(1L);

        when(gestureRepository.save(any())).thenReturn(createdGesture);

        Gesture result = gestureService.create(gesture);

        assertEquals(result.getIdGesture(), createdGesture.getIdGesture());
    }

    @Test
    @DisplayName("When create With Valid Attributes And With An Id Then Returns IllegalArgumentException")
    void createIllegalArgumentException() throws  IllegalArgumentException{
        String expectedMessage = "Body should not contain id";
        Gesture gesture = new Gesture();
        gesture.setStatus(1);
        gesture.setIdGesture(1L);

        Throwable exception = Assertions.catchThrowable( () -> {
            Gesture result = gestureService.create(gesture);
        });

        Assertions.assertThat(exception).isInstanceOf(IllegalArgumentException.class).hasMessage(expectedMessage);
    }


    @Test
    @DisplayName("When findById With Valid Id Then Returns Gesture")
    void findById() {
        Gesture gesture = new Gesture();
        gesture.setStatus(1);
        gesture.setIdGesture(1L);

        when(gestureRepository.findById(1L)).thenReturn(Optional.of(gesture));

        final Gesture result = gestureService.findById(1L);

        assertEquals(result.getIdGesture(), gesture.getIdGesture());
    }
    @Test
    @DisplayName("When findById With Invalid Id Then Returns Null")
    void findByIdNull() {
        assertNull(gestureService.findById(1L));
    }


    @Test
    @DisplayName("When findAll Then Returns Gesture List")
    void findAll() {
        Gesture gesture = new Gesture();
        gesture.setStatus(1);

        Gesture gesture2 = new Gesture();
        gesture2.setStatus(2);

        List<Gesture> gestureList = new ArrayList<>();
        gestureList.add(gesture);
        gestureList.add(gesture2);

        when(gestureRepository.findAll()).thenReturn( gestureList);

        List<Gesture> result = gestureService.findAll();

        assertEquals(result.size(), 2);
    }

    @Test
    @DisplayName("When update With Valid Attributes And Valid Id Then Returns Gesture")
    void update() throws IllegalAccessException {
        Gesture gesture = new Gesture();
        gesture.setStatus(1);

        Gesture retrievedGesture = new Gesture();
        retrievedGesture.setIdGesture(4L);
        retrievedGesture.setStatus(2);

        when(gestureRepository.findById(any())).thenReturn(Optional.of(retrievedGesture));
        when(gestureRepository.save(any())).thenReturn(retrievedGesture);

        final Gesture result = gestureService.update(gesture);


        assertEquals(retrievedGesture.getIdGesture(), result.getIdGesture());
        assertEquals(retrievedGesture.getStatus(), result.getStatus());
    }

    @Test
    @DisplayName("When update With Valid Attributes And Invalid Id Then Returns NoSuchElementException")
    void updateNoSuchElementException() throws IllegalAccessException {
        String expectedMessage = "Element does not exist in database";
        Gesture retrievedGesture = new Gesture();
        retrievedGesture.setIdGesture(4L);
        retrievedGesture.setStatus(1);

        Throwable exception = Assertions.catchThrowable( () -> {
            Gesture result = gestureService.update(retrievedGesture);
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }



    @Test
    @DisplayName("When change With Valid Attributes And Valid Id Then Returns Gesture")
    void change() throws IllegalAccessException {
        Gesture gesture = new Gesture();
        gesture.setStatus(1);

        Gesture retrievedGesture = new Gesture();
        retrievedGesture.setIdGesture(4L);
        retrievedGesture.setStatus(2);

        when(gestureRepository.findById(any())).thenReturn(Optional.of(retrievedGesture));
        when(gestureRepository.save(any())).thenReturn(retrievedGesture);

        final Gesture result = gestureService.change(gesture);

        assertEquals(retrievedGesture.getIdGesture(), result.getIdGesture());
        assertEquals(retrievedGesture.getStatus(), result.getStatus());
    }

    @Test
    @DisplayName("When change With Valid Attributes And Invalid Id Then Returns NoSuchElementException")
    void changeNoSuchElementException() throws IllegalAccessException {
        String expectedMessage = "Element does not exist in database";
        Gesture retrievedGesture = new Gesture();
        retrievedGesture.setIdGesture(4L);
        retrievedGesture.setStatus(1);

        Throwable exception = Assertions.catchThrowable( () -> {
            Gesture result = gestureService.change(retrievedGesture);
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When removeById With Valid Id Then Returns Gesture")
    void removeById(){
        Gesture retrievedGesture = new Gesture();
        retrievedGesture.setIdGesture(4L);
        retrievedGesture.setStatus(2);

        when(gestureRepository.findById(4L)).thenReturn(Optional.of(retrievedGesture));

        gestureService.removeById(4L);
        verify(gestureRepository).deleteById(4L);
    }


    @Test
    @DisplayName("When removeById Invalid Id Then Returns NoSuchElementException")
    void removeByIdNoSuchElementException() {
        String expectedMessage = "Element does not exist in database";

        Throwable exception = Assertions.catchThrowable( () -> {
            Gesture result = gestureService.removeById(4L);
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When getPossibleStatus Then Returns Gesture List")
    void getPossibleStatus() {
        GestureStatusConstants gestureStatusConstants = GestureStatusConstants.DELETED;
        GestureStatusConstants gestureStatusConstants2 = GestureStatusConstants.ACTIVE;

        List<GestureStatusConstants> gestureStatusConstantsList = new ArrayList<>();
        gestureStatusConstantsList.add(gestureStatusConstants);
        gestureStatusConstantsList.add(gestureStatusConstants2);

        when(gestureServiceMock.getPossibleStatus()).thenReturn(Arrays.stream(GestureStatusConstants.values()).collect(Collectors.toList()));

        List<GestureStatusConstants> result = gestureService.getPossibleStatus();

        assertEquals(result, gestureStatusConstantsList);
    }
}
