package com.orchestrator.orchestrator.business.impl;

import com.orchestrator.orchestrator.business.InstrumentService;
import com.orchestrator.orchestrator.model.Instrument;
import com.orchestrator.orchestrator.repository.InstrumentRepository;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import com.orchestrator.orchestrator.utils.constants.InstrumentStatusConstants;
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

public class InstrumentServiceImplTest {

    @Mock
    private InstrumentRepository instrumentRepository;

    private GeneralUtils generalUtils;
    private InstrumentService instrumentService;

    @Mock
    private InstrumentService instrumentServiceMock = mock(InstrumentServiceImpl.class);

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        generalUtils = new GeneralUtilsImpl();
        instrumentService = new InstrumentServiceImpl(instrumentRepository, generalUtils) {
        };
    }

    @Test
    @DisplayName("When create With Valid Attributes And Without An Id Then Returns Instrument")
    void create() {
        Instrument instrument = new Instrument();
        instrument.setStatus(1);

        Instrument createdInstrument = new Instrument();
        createdInstrument.setIdInstrument(1L);

        when(instrumentRepository.save(any())).thenReturn(createdInstrument);

        Instrument result = instrumentService.create(instrument);

        assertEquals(result.getIdInstrument(), createdInstrument.getIdInstrument());
    }

    @Test
    @DisplayName("When create With Valid Attributes And With An Id Then Returns IllegalArgumentException")
    void createIllegalArgumentException() throws  IllegalArgumentException{
        String expectedMessage = "Body should not contain id";
        Instrument instrument = new Instrument();
        instrument.setStatus(1);
        instrument.setIdInstrument(1L);

        Throwable exception = Assertions.catchThrowable( () -> {
            Instrument result = instrumentService.create(instrument);
        });

        Assertions.assertThat(exception).isInstanceOf(IllegalArgumentException.class).hasMessage(expectedMessage);
    }


    @Test
    @DisplayName("When findById With Valid Id Then Returns Instrument")
    void findById() {
        Instrument instrument = new Instrument();
        instrument.setStatus(1);
        instrument.setIdInstrument(1L);

        when(instrumentRepository.findById(1L)).thenReturn(Optional.of(instrument));

        final Instrument result = instrumentService.findById(1L);

        assertEquals(result.getIdInstrument(), instrument.getIdInstrument());
    }
    @Test
    @DisplayName("When findById With Invalid Id Then Returns Null")
    void findByIdNull() {
        assertNull(instrumentService.findById(1L));
    }


    @Test
    @DisplayName("When findAll Then Returns Instrument List")
    void findAll() {
        Instrument instrument = new Instrument();
        instrument.setStatus(1);

        Instrument instrument2 = new Instrument();
        instrument2.setStatus(2);

        List<Instrument> instrumentList = new ArrayList<>();
        instrumentList.add(instrument);
        instrumentList.add(instrument2);

        when(instrumentRepository.findAll()).thenReturn( instrumentList);

        List<Instrument> result = instrumentService.findAll();

        assertEquals(result.size(), 2);
    }

    @Test
    @DisplayName("When update With Valid Attributes And Valid Id Then Returns Instrument")
    void update() throws IllegalAccessException {
        Instrument instrument = new Instrument();
        instrument.setStatus(1);

        Instrument retrievedInstrument = new Instrument();
        retrievedInstrument.setIdInstrument(4L);
        retrievedInstrument.setStatus(2);

        when(instrumentRepository.findById(any())).thenReturn(Optional.of(retrievedInstrument));
        when(instrumentRepository.save(any())).thenReturn(retrievedInstrument);

        final Instrument result = instrumentService.update(instrument);


        assertEquals(retrievedInstrument.getIdInstrument(), result.getIdInstrument());
        assertEquals(retrievedInstrument.getStatus(), result.getStatus());
    }

    @Test
    @DisplayName("When update With Valid Attributes And Invalid Id Then Returns NoSuchElementException")
    void updateNoSuchElementException() throws IllegalAccessException {
        String expectedMessage = "Element does not exist in database";
        Instrument retrievedInstrument = new Instrument();
        retrievedInstrument.setIdInstrument(4L);
        retrievedInstrument.setStatus(1);

        Throwable exception = Assertions.catchThrowable( () -> {
            Instrument result = instrumentService.update(retrievedInstrument);
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }



    @Test
    @DisplayName("When change With Valid Attributes And Valid Id Then Returns Instrument")
    void change() throws IllegalAccessException {
        Instrument instrument = new Instrument();
        instrument.setStatus(1);

        Instrument retrievedInstrument = new Instrument();
        retrievedInstrument.setIdInstrument(4L);
        retrievedInstrument.setStatus(2);

        when(instrumentRepository.findById(any())).thenReturn(Optional.of(retrievedInstrument));
        when(instrumentRepository.save(any())).thenReturn(retrievedInstrument);

        final Instrument result = instrumentService.change(instrument);

        assertEquals(retrievedInstrument.getIdInstrument(), result.getIdInstrument());
        assertEquals(retrievedInstrument.getStatus(), result.getStatus());
    }

    @Test
    @DisplayName("When change With Valid Attributes And Invalid Id Then Returns NoSuchElementException")
    void changeNoSuchElementException() throws IllegalAccessException {
        String expectedMessage = "Element does not exist in database";
        Instrument retrievedInstrument = new Instrument();
        retrievedInstrument.setIdInstrument(4L);
        retrievedInstrument.setStatus(1);

        Throwable exception = Assertions.catchThrowable( () -> {
            Instrument result = instrumentService.change(retrievedInstrument);
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When removeById With Valid Id Then Returns Instrument")
    void removeById(){
        Instrument retrievedInstrument = new Instrument();
        retrievedInstrument.setIdInstrument(4L);
        retrievedInstrument.setStatus(2);

        when(instrumentRepository.findById(4L)).thenReturn(Optional.of(retrievedInstrument));

        instrumentService.removeById(4L);
        verify(instrumentRepository).deleteById(4L);
    }


    @Test
    @DisplayName("When removeById Invalid Id Then Returns NoSuchElementException")
    void removeByIdNoSuchElementException() {
        String expectedMessage = "Element does not exist in database";

        Throwable exception = Assertions.catchThrowable( () -> {
            Instrument result = instrumentService.removeById(4L);
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When getPossibleStatus Then Returns Instrument List")
    void getPossibleStatus() {
        InstrumentStatusConstants instrumentStatusConstants = InstrumentStatusConstants.DELETED;
        InstrumentStatusConstants instrumentStatusConstants2 = InstrumentStatusConstants.ACTIVE;

        List<InstrumentStatusConstants> instrumentStatusConstantsList = new ArrayList<>();
        instrumentStatusConstantsList.add(instrumentStatusConstants);
        instrumentStatusConstantsList.add(instrumentStatusConstants2);

        when(instrumentServiceMock.getPossibleStatus()).thenReturn(Arrays.stream(InstrumentStatusConstants.values()).collect(Collectors.toList()));

        List<InstrumentStatusConstants> result = instrumentService.getPossibleStatus();

        assertEquals(result, instrumentStatusConstantsList);
    }

    @Test
    @DisplayName("When findInstrumentByName Then Returns Instrument List")
    void findInstrumentByName() {
        Instrument instrument = new Instrument();
        instrument.setStatus(1);
        instrument.setName("Big Trumpet");

        Instrument instrument2 = new Instrument();
        instrument2.setStatus(2);
        instrument.setName("Small Trumpet");

        List<Instrument> instrumentList = new ArrayList<>();
        instrumentList.add(instrument);
        instrumentList.add(instrument2);

        when(instrumentService.findInstrumentsByName("Trumpet")).thenReturn(instrumentList);

        List<Instrument> result = instrumentService.findInstrumentsByName("Trumpet");

        assertEquals(result.size(), 2);
    }
}
