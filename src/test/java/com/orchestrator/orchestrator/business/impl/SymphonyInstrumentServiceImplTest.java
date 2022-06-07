package com.orchestrator.orchestrator.business.impl;

import com.orchestrator.orchestrator.business.SymphonyInstrumentService;
import com.orchestrator.orchestrator.model.Instrument;
import com.orchestrator.orchestrator.model.SymphonyInstrument;
import com.orchestrator.orchestrator.repository.SymphonyInstrumentRepository;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import com.orchestrator.orchestrator.utils.constants.SymphonyInstrumentStatusConstants;
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

public class SymphonyInstrumentServiceImplTest {

    @Mock
    private SymphonyInstrumentRepository symphonySymphonyInstrumentRepository;

    private GeneralUtils generalUtils;
    private SymphonyInstrumentService symphonySymphonyInstrumentService;

    @Mock
    private SymphonyInstrumentService symphonySymphonyInstrumentServiceMock = mock(SymphonyInstrumentServiceImpl.class);

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        generalUtils = new GeneralUtilsImpl();
        symphonySymphonyInstrumentService = new SymphonyInstrumentServiceImpl(symphonySymphonyInstrumentRepository, generalUtils) {
        };
    }

    @Test
    @DisplayName("When create With Valid Attributes And Without An Id Then Returns SymphonyInstrument")
    void create() {
        SymphonyInstrument symphonySymphonyInstrument = new SymphonyInstrument();
        symphonySymphonyInstrument.setStatus(1);

        SymphonyInstrument createdSymphonyInstrument = new SymphonyInstrument();
        createdSymphonyInstrument.setIdSymphonyInstrument(1L);

        when(symphonySymphonyInstrumentRepository.save(any())).thenReturn(createdSymphonyInstrument);

        SymphonyInstrument result = symphonySymphonyInstrumentService.create(symphonySymphonyInstrument);

        assertEquals(result.getIdSymphonyInstrument(), createdSymphonyInstrument.getIdSymphonyInstrument());
    }

    @Test
    @DisplayName("When create With Valid Attributes And With An Id Then Returns IllegalArgumentException")
    void createIllegalArgumentException() throws  IllegalArgumentException{
        String expectedMessage = "Body should not contain id";
        SymphonyInstrument symphonySymphonyInstrument = new SymphonyInstrument();
        symphonySymphonyInstrument.setStatus(1);
        symphonySymphonyInstrument.setIdSymphonyInstrument(1L);

        Throwable exception = Assertions.catchThrowable( () -> {
            SymphonyInstrument result = symphonySymphonyInstrumentService.create(symphonySymphonyInstrument);
        });

        Assertions.assertThat(exception).isInstanceOf(IllegalArgumentException.class).hasMessage(expectedMessage);
    }


    @Test
    @DisplayName("When findById With Valid Id Then Returns SymphonyInstrument")
    void findById() {
        SymphonyInstrument symphonySymphonyInstrument = new SymphonyInstrument();
        symphonySymphonyInstrument.setStatus(1);
        symphonySymphonyInstrument.setIdSymphonyInstrument(1L);

        when(symphonySymphonyInstrumentRepository.findById(1L)).thenReturn(Optional.of(symphonySymphonyInstrument));

        final SymphonyInstrument result = symphonySymphonyInstrumentService.findById(1L);

        assertEquals(result.getIdSymphonyInstrument(), symphonySymphonyInstrument.getIdSymphonyInstrument());
    }
    @Test
    @DisplayName("When findById With Invalid Id Then Returns Null")
    void findByIdNull() {
        assertNull(symphonySymphonyInstrumentService.findById(1L));
    }


    @Test
    @DisplayName("When findAll Then Returns SymphonyInstrument List")
    void findAll() {
        SymphonyInstrument symphonySymphonyInstrument = new SymphonyInstrument();
        symphonySymphonyInstrument.setStatus(1);

        SymphonyInstrument symphonySymphonyInstrument2 = new SymphonyInstrument();
        symphonySymphonyInstrument2.setStatus(2);

        List<SymphonyInstrument> symphonySymphonyInstrumentList = new ArrayList<>();
        symphonySymphonyInstrumentList.add(symphonySymphonyInstrument);
        symphonySymphonyInstrumentList.add(symphonySymphonyInstrument2);

        when(symphonySymphonyInstrumentRepository.findAll()).thenReturn( symphonySymphonyInstrumentList);

        List<SymphonyInstrument> result = symphonySymphonyInstrumentService.findAll();

        assertEquals(result.size(), 2);
    }

    @Test
    @DisplayName("When update With Valid Attributes And Valid Id Then Returns SymphonyInstrument")
    void update() throws IllegalAccessException {
        SymphonyInstrument symphonySymphonyInstrument = new SymphonyInstrument();
        symphonySymphonyInstrument.setStatus(1);

        SymphonyInstrument retrievedSymphonyInstrument = new SymphonyInstrument();
        retrievedSymphonyInstrument.setIdSymphonyInstrument(4L);
        retrievedSymphonyInstrument.setStatus(2);

        when(symphonySymphonyInstrumentRepository.findById(any())).thenReturn(Optional.of(retrievedSymphonyInstrument));
        when(symphonySymphonyInstrumentRepository.save(any())).thenReturn(retrievedSymphonyInstrument);

        final SymphonyInstrument result = symphonySymphonyInstrumentService.update(symphonySymphonyInstrument);


        assertEquals(retrievedSymphonyInstrument.getIdSymphonyInstrument(), result.getIdSymphonyInstrument());
        assertEquals(retrievedSymphonyInstrument.getStatus(), result.getStatus());
    }

    @Test
    @DisplayName("When update With Valid Attributes And Invalid Id Then Returns NoSuchElementException")
    void updateNoSuchElementException() throws IllegalAccessException {
        String expectedMessage = "Element does not exist in database";
        SymphonyInstrument retrievedSymphonyInstrument = new SymphonyInstrument();
        retrievedSymphonyInstrument.setIdSymphonyInstrument(4L);
        retrievedSymphonyInstrument.setStatus(1);

        Throwable exception = Assertions.catchThrowable( () -> {
            SymphonyInstrument result = symphonySymphonyInstrumentService.update(retrievedSymphonyInstrument);
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }



    @Test
    @DisplayName("When change With Valid Attributes And Valid Id Then Returns SymphonyInstrument")
    void change() throws IllegalAccessException {
        SymphonyInstrument symphonySymphonyInstrument = new SymphonyInstrument();
        symphonySymphonyInstrument.setStatus(1);

        SymphonyInstrument retrievedSymphonyInstrument = new SymphonyInstrument();
        retrievedSymphonyInstrument.setIdSymphonyInstrument(4L);
        retrievedSymphonyInstrument.setStatus(2);

        when(symphonySymphonyInstrumentRepository.findById(any())).thenReturn(Optional.of(retrievedSymphonyInstrument));
        when(symphonySymphonyInstrumentRepository.save(any())).thenReturn(retrievedSymphonyInstrument);

        final SymphonyInstrument result = symphonySymphonyInstrumentService.change(symphonySymphonyInstrument);

        assertEquals(retrievedSymphonyInstrument.getIdSymphonyInstrument(), result.getIdSymphonyInstrument());
        assertEquals(retrievedSymphonyInstrument.getStatus(), result.getStatus());
    }

    @Test
    @DisplayName("When change With Valid Attributes And Invalid Id Then Returns NoSuchElementException")
    void changeNoSuchElementException() throws IllegalAccessException {
        String expectedMessage = "Element does not exist in database";
        SymphonyInstrument retrievedSymphonyInstrument = new SymphonyInstrument();
        retrievedSymphonyInstrument.setIdSymphonyInstrument(4L);
        retrievedSymphonyInstrument.setStatus(1);

        Throwable exception = Assertions.catchThrowable( () -> {
            SymphonyInstrument result = symphonySymphonyInstrumentService.change(retrievedSymphonyInstrument);
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When removeById With Valid Id Then Returns SymphonyInstrument")
    void removeById(){
        SymphonyInstrument retrievedSymphonyInstrument = new SymphonyInstrument();
        retrievedSymphonyInstrument.setIdSymphonyInstrument(4L);
        retrievedSymphonyInstrument.setStatus(2);

        when(symphonySymphonyInstrumentRepository.findById(4L)).thenReturn(Optional.of(retrievedSymphonyInstrument));

        symphonySymphonyInstrumentService.removeById(4L);
        verify(symphonySymphonyInstrumentRepository).deleteById(4L);
    }


    @Test
    @DisplayName("When removeById Invalid Id Then Returns NoSuchElementException")
    void removeByIdNoSuchElementException() {
        String expectedMessage = "Element does not exist in database";

        Throwable exception = Assertions.catchThrowable( () -> {
            SymphonyInstrument result = symphonySymphonyInstrumentService.removeById(4L);
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When getPossibleStatus Then Returns SymphonyInstrument List")
    void getPossibleStatus() {
        SymphonyInstrumentStatusConstants symphonySymphonyInstrumentStatusConstants = SymphonyInstrumentStatusConstants.DELETED;
        SymphonyInstrumentStatusConstants symphonySymphonyInstrumentStatusConstants2 = SymphonyInstrumentStatusConstants.ACTIVE;

        List<SymphonyInstrumentStatusConstants> symphonySymphonyInstrumentStatusConstantsList = new ArrayList<>();
        symphonySymphonyInstrumentStatusConstantsList.add(symphonySymphonyInstrumentStatusConstants);
        symphonySymphonyInstrumentStatusConstantsList.add(symphonySymphonyInstrumentStatusConstants2);

        when(symphonySymphonyInstrumentServiceMock.getPossibleStatus()).thenReturn(Arrays.stream(SymphonyInstrumentStatusConstants.values()).collect(Collectors.toList()));

        List<SymphonyInstrumentStatusConstants> result = symphonySymphonyInstrumentService.getPossibleStatus();

        assertEquals(result, symphonySymphonyInstrumentStatusConstantsList);
    }

    //Integration Tests

    @Test
    @DisplayName("When findInstrumentBySymphony Then Returns Instrument List")
    void findInstrumentBySymphony() {
        Instrument instrument = new Instrument();
        instrument.setStatus(1);

        Instrument instrument2 = new Instrument();
        instrument2.setStatus(2);

        List<Instrument> instrumentList = new ArrayList<>();
        instrumentList.add(instrument);
        instrumentList.add(instrument2);

        when(symphonySymphonyInstrumentService.findInstrumentsBySymphony(1L)).thenReturn(instrumentList);

        List<Instrument> result = symphonySymphonyInstrumentService.findInstrumentsBySymphony(1L);

        assertEquals(result.size(), 2);
    }
}
