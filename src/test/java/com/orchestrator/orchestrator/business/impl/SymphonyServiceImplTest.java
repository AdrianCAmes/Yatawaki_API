package com.orchestrator.orchestrator.business.impl;

import com.orchestrator.orchestrator.business.SymphonyService;
import com.orchestrator.orchestrator.model.Symphony;
import com.orchestrator.orchestrator.repository.SymphonyRepository;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import com.orchestrator.orchestrator.utils.impl.GeneralUtilsImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.assertj.core.api.Assertions;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SymphonyServiceImplTest {

    @Mock
    private SymphonyRepository symphonyRepository;

    private GeneralUtils generalUtils;
    private SymphonyService symphonyService;

    @Mock
    private SymphonyService symphonyServiceMock = mock(SymphonyServiceImpl.class);

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        generalUtils = new GeneralUtilsImpl();
        symphonyService = new SymphonyServiceImpl(symphonyRepository, generalUtils) {
        };
    }

    @Test
    @DisplayName("When create With Valid Attributes And Without An Id Then Returns Symphony")
    void create() {
        Symphony symphony = new Symphony();
        symphony.setName("My name");

        Symphony createdSymphony = new Symphony();
        createdSymphony.setIdUnlockable(1L);

        when(symphonyRepository.save(any())).thenReturn(createdSymphony);

        Symphony result = symphonyService.create(symphony);

        assertEquals(result.getIdUnlockable(), createdSymphony.getIdUnlockable());
    }

    @Test
    @DisplayName("When create With Valid Attributes And With An Id Then Returns IllegalArgumentException")
    void createIllegalArgumentException() throws  IllegalArgumentException{
        String expectedMessage = "Body should not contain id";
        Symphony symphony = new Symphony();
        symphony.setName("My name");
        symphony.setIdUnlockable(1L);

        Throwable exception = Assertions.catchThrowable( () -> {
            Symphony result = symphonyService.create(symphony);
        });

        Assertions.assertThat(exception).isInstanceOf(IllegalArgumentException.class).hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When findById With Valid Id Then Returns Symphony")
    void findById() {
        Symphony symphony = new Symphony();
        symphony.setName("My name");

        when(symphonyRepository.findById(1L)).thenReturn(Optional.ofNullable(symphony));

        final Symphony result = symphonyService.findById(1L);

        assertEquals(symphony.toString(), symphony.toString());
    }

    @Test
    @DisplayName("When findById With Invalid Id Then Returns Null")
    void findByIdNull() {
        assertNull(symphonyService.findById(1L));
    }


    @Test
    @DisplayName("When findAll Then Returns Symphony List")
    void findAll() {
        Symphony symphony = new Symphony();
        symphony.setName("My name");

        Symphony symphony2 = new Symphony();
        symphony2.setName("My name 2");

        List<Symphony> symphonyList = new ArrayList<>();
        symphonyList.add(symphony);
        symphonyList.add(symphony2);

        when(symphonyRepository.findAll()).thenReturn( symphonyList);

        List<Symphony> result = symphonyService.findAll();

        assertEquals(result.size(), 2);
    }

    @Test
    @DisplayName("When update With Valid Attributes And Valid Id Then Returns Symphony")
    void update() throws IllegalAccessException {
        Symphony symphony = new Symphony();
        symphony.setName("New Name");

        Symphony retrievedSymphony = new Symphony();
        retrievedSymphony.setIdUnlockable(4L);
        retrievedSymphony.setName("Old Name");

        when(symphonyRepository.findById(any())).thenReturn(Optional.of(retrievedSymphony));
        when(symphonyRepository.save(any())).thenReturn(retrievedSymphony);

        final Symphony result = symphonyService.update(symphony);

        assertEquals(retrievedSymphony.toString(), result.toString());
    }

    @Test
    @DisplayName("When update With Valid Attributes And Invalid Id Then Returns NoSuchElementException")
    void updateNoSuchElementException() throws IllegalAccessException {
        String expectedMessage = "Element does not exist in database";
        Symphony retrievedSymphony = new Symphony();
        retrievedSymphony.setIdUnlockable(4L);
        retrievedSymphony.setName("Name");

        Throwable exception = Assertions.catchThrowable( () -> {
            Symphony result = symphonyService.update(retrievedSymphony);
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }



    @Test
    @DisplayName("When change With Valid Attributes And Valid Id Then Returns Symphony")
    void change() throws IllegalAccessException {
        Symphony symphony = new Symphony();
        symphony.setName("New Name");

        Symphony retrievedSymphony = new Symphony();
        retrievedSymphony.setIdUnlockable(4L);
        retrievedSymphony.setName("Old Name");

        when(symphonyRepository.findById(any())).thenReturn(Optional.of(retrievedSymphony));
        when(symphonyRepository.save(any())).thenReturn(retrievedSymphony);

        final Symphony result = symphonyService.change(symphony);

        assertEquals(retrievedSymphony.toString(), result.toString());
    }

    @Test
    @DisplayName("When change With Valid Attributes And Invalid Id Then Returns NoSuchElementException")
    void changeNoSuchElementException() throws IllegalAccessException {
        String expectedMessage = "Element does not exist in database";
        Symphony retrievedSymphony = new Symphony();
        retrievedSymphony.setIdUnlockable(4L);
        retrievedSymphony.setName("Name");

        Throwable exception = Assertions.catchThrowable( () -> {
            Symphony result = symphonyService.change(retrievedSymphony);
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When removeById With Valid Id Then Returns Symphony")
    void removeById(){
        Symphony retrievedSymphony = new Symphony();
        retrievedSymphony.setIdUnlockable(4L);
        retrievedSymphony.setName("Name");

        when(symphonyRepository.findById(4L)).thenReturn(Optional.of(retrievedSymphony));

        symphonyService.removeById(4L);
        verify(symphonyRepository).deleteById(4L);
    }


    @Test
    @DisplayName("When removeById Invalid Id Then Returns NoSuchElementException")
    void removeByIdNoSuchElementException() {
        String expectedMessage = "Element does not exist in database";

        Throwable exception = Assertions.catchThrowable( () -> {
            Symphony result = symphonyService.removeById(4L);
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }



}
