package com.orchestrator.orchestrator.business.impl;

import com.orchestrator.orchestrator.business.SymphonyGestureService;
import com.orchestrator.orchestrator.model.SymphonyGesture;
import com.orchestrator.orchestrator.repository.SymphonyGestureRepository;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import com.orchestrator.orchestrator.utils.constants.SymphonyGestureStatusConstants;
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

public class SymphonyGestureServiceImplTest {

    @Mock
    private SymphonyGestureRepository symphonySymphonyGestureRepository;

    private GeneralUtils generalUtils;
    private SymphonyGestureService symphonySymphonyGestureService;

    @Mock
    private SymphonyGestureService symphonySymphonyGestureServiceMock = mock(SymphonyGestureServiceImpl.class);

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        generalUtils = new GeneralUtilsImpl();
        symphonySymphonyGestureService = new SymphonyGestureServiceImpl(symphonySymphonyGestureRepository, generalUtils) {
        };
    }

    @Test
    @DisplayName("When create With Valid Attributes And Without An Id Then Returns SymphonyGesture")
    void create() {
        SymphonyGesture symphonySymphonyGesture = new SymphonyGesture();
        symphonySymphonyGesture.setStatus(1);

        SymphonyGesture createdSymphonyGesture = new SymphonyGesture();
        createdSymphonyGesture.setIdSymphonyGesture(1L);

        when(symphonySymphonyGestureRepository.save(any())).thenReturn(createdSymphonyGesture);

        SymphonyGesture result = symphonySymphonyGestureService.create(symphonySymphonyGesture);

        assertEquals(result.getIdSymphonyGesture(), createdSymphonyGesture.getIdSymphonyGesture());
    }

    @Test
    @DisplayName("When create With Valid Attributes And With An Id Then Returns IllegalArgumentException")
    void createIllegalArgumentException() throws  IllegalArgumentException{
        String expectedMessage = "Body should not contain id";
        SymphonyGesture symphonySymphonyGesture = new SymphonyGesture();
        symphonySymphonyGesture.setStatus(1);
        symphonySymphonyGesture.setIdSymphonyGesture(1L);

        Throwable exception = Assertions.catchThrowable( () -> {
            SymphonyGesture result = symphonySymphonyGestureService.create(symphonySymphonyGesture);
        });

        Assertions.assertThat(exception).isInstanceOf(IllegalArgumentException.class).hasMessage(expectedMessage);
    }


    @Test
    @DisplayName("When findById With Valid Id Then Returns SymphonyGesture")
    void findById() {
        SymphonyGesture symphonySymphonyGesture = new SymphonyGesture();
        symphonySymphonyGesture.setStatus(1);
        symphonySymphonyGesture.setIdSymphonyGesture(1L);

        when(symphonySymphonyGestureRepository.findById(1L)).thenReturn(Optional.of(symphonySymphonyGesture));

        final SymphonyGesture result = symphonySymphonyGestureService.findById(1L);

        assertEquals(result.getIdSymphonyGesture(), symphonySymphonyGesture.getIdSymphonyGesture());
    }
    @Test
    @DisplayName("When findById With Invalid Id Then Returns Null")
    void findByIdNull() {
        assertNull(symphonySymphonyGestureService.findById(1L));
    }


    @Test
    @DisplayName("When findAll Then Returns SymphonyGesture List")
    void findAll() {
        SymphonyGesture symphonySymphonyGesture = new SymphonyGesture();
        symphonySymphonyGesture.setStatus(1);

        SymphonyGesture symphonySymphonyGesture2 = new SymphonyGesture();
        symphonySymphonyGesture2.setStatus(2);

        List<SymphonyGesture> symphonySymphonyGestureList = new ArrayList<>();
        symphonySymphonyGestureList.add(symphonySymphonyGesture);
        symphonySymphonyGestureList.add(symphonySymphonyGesture2);

        when(symphonySymphonyGestureRepository.findAll()).thenReturn( symphonySymphonyGestureList);

        List<SymphonyGesture> result = symphonySymphonyGestureService.findAll();

        assertEquals(result.size(), 2);
    }

    @Test
    @DisplayName("When update With Valid Attributes And Valid Id Then Returns SymphonyGesture")
    void update() throws IllegalAccessException {
        SymphonyGesture symphonySymphonyGesture = new SymphonyGesture();
        symphonySymphonyGesture.setStatus(1);

        SymphonyGesture retrievedSymphonyGesture = new SymphonyGesture();
        retrievedSymphonyGesture.setIdSymphonyGesture(4L);
        retrievedSymphonyGesture.setStatus(2);

        when(symphonySymphonyGestureRepository.findById(any())).thenReturn(Optional.of(retrievedSymphonyGesture));
        when(symphonySymphonyGestureRepository.save(any())).thenReturn(retrievedSymphonyGesture);

        final SymphonyGesture result = symphonySymphonyGestureService.update(symphonySymphonyGesture);


        assertEquals(retrievedSymphonyGesture.getIdSymphonyGesture(), result.getIdSymphonyGesture());
        assertEquals(retrievedSymphonyGesture.getStatus(), result.getStatus());
    }

    @Test
    @DisplayName("When update With Valid Attributes And Invalid Id Then Returns NoSuchElementException")
    void updateNoSuchElementException() throws IllegalAccessException {
        String expectedMessage = "Element does not exist in database";
        SymphonyGesture retrievedSymphonyGesture = new SymphonyGesture();
        retrievedSymphonyGesture.setIdSymphonyGesture(4L);
        retrievedSymphonyGesture.setStatus(1);

        Throwable exception = Assertions.catchThrowable( () -> {
            SymphonyGesture result = symphonySymphonyGestureService.update(retrievedSymphonyGesture);
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }



    @Test
    @DisplayName("When change With Valid Attributes And Valid Id Then Returns SymphonyGesture")
    void change() throws IllegalAccessException {
        SymphonyGesture symphonySymphonyGesture = new SymphonyGesture();
        symphonySymphonyGesture.setStatus(1);

        SymphonyGesture retrievedSymphonyGesture = new SymphonyGesture();
        retrievedSymphonyGesture.setIdSymphonyGesture(4L);
        retrievedSymphonyGesture.setStatus(2);

        when(symphonySymphonyGestureRepository.findById(any())).thenReturn(Optional.of(retrievedSymphonyGesture));
        when(symphonySymphonyGestureRepository.save(any())).thenReturn(retrievedSymphonyGesture);

        final SymphonyGesture result = symphonySymphonyGestureService.change(symphonySymphonyGesture);

        assertEquals(retrievedSymphonyGesture.getIdSymphonyGesture(), result.getIdSymphonyGesture());
        assertEquals(retrievedSymphonyGesture.getStatus(), result.getStatus());
    }

    @Test
    @DisplayName("When change With Valid Attributes And Invalid Id Then Returns NoSuchElementException")
    void changeNoSuchElementException() throws IllegalAccessException {
        String expectedMessage = "Element does not exist in database";
        SymphonyGesture retrievedSymphonyGesture = new SymphonyGesture();
        retrievedSymphonyGesture.setIdSymphonyGesture(4L);
        retrievedSymphonyGesture.setStatus(1);

        Throwable exception = Assertions.catchThrowable( () -> {
            SymphonyGesture result = symphonySymphonyGestureService.change(retrievedSymphonyGesture);
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When removeById With Valid Id Then Returns SymphonyGesture")
    void removeById(){
        SymphonyGesture retrievedSymphonyGesture = new SymphonyGesture();
        retrievedSymphonyGesture.setIdSymphonyGesture(4L);
        retrievedSymphonyGesture.setStatus(2);

        when(symphonySymphonyGestureRepository.findById(4L)).thenReturn(Optional.of(retrievedSymphonyGesture));

        symphonySymphonyGestureService.removeById(4L);
        verify(symphonySymphonyGestureRepository).deleteById(4L);
    }


    @Test
    @DisplayName("When removeById Invalid Id Then Returns NoSuchElementException")
    void removeByIdNoSuchElementException() {
        String expectedMessage = "Element does not exist in database";

        Throwable exception = Assertions.catchThrowable( () -> {
            SymphonyGesture result = symphonySymphonyGestureService.removeById(4L);
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When getPossibleStatus Then Returns SymphonyGesture List")
    void getPossibleStatus() {
        SymphonyGestureStatusConstants symphonySymphonyGestureStatusConstants = SymphonyGestureStatusConstants.DELETED;
        SymphonyGestureStatusConstants symphonySymphonyGestureStatusConstants2 = SymphonyGestureStatusConstants.ACTIVE;

        List<SymphonyGestureStatusConstants> symphonySymphonyGestureStatusConstantsList = new ArrayList<>();
        symphonySymphonyGestureStatusConstantsList.add(symphonySymphonyGestureStatusConstants);
        symphonySymphonyGestureStatusConstantsList.add(symphonySymphonyGestureStatusConstants2);

        when(symphonySymphonyGestureServiceMock.getPossibleStatus()).thenReturn(Arrays.stream(SymphonyGestureStatusConstants.values()).collect(Collectors.toList()));

        List<SymphonyGestureStatusConstants> result = symphonySymphonyGestureService.getPossibleStatus();

        assertEquals(result, symphonySymphonyGestureStatusConstantsList);
    }
}
