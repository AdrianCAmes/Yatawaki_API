package com.orchestrator.orchestrator.business.impl;

import com.orchestrator.orchestrator.business.UnlockableService;
import com.orchestrator.orchestrator.model.Unlockable;
import com.orchestrator.orchestrator.repository.UnlockableRepository;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import com.orchestrator.orchestrator.utils.constants.UnlockableRarenessConstants;
import com.orchestrator.orchestrator.utils.constants.UnlockableStatusConstants;
import com.orchestrator.orchestrator.utils.constants.UnlockerTypeConstants;
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

public class UnlockableServiceImplTest {

    @Mock
    private UnlockableRepository unlockableRepository;

    private GeneralUtils generalUtils;
    private UnlockableService unlockableService;

    @Mock
    private UnlockableService unlockableServiceMock = mock(UnlockableServiceImpl.class);

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        generalUtils = new GeneralUtilsImpl();
        unlockableService = new UnlockableServiceImpl(unlockableRepository, generalUtils) {
        };
    }

    @Test
    @DisplayName("When create With Valid Attributes And Without An Id Then Returns Unlockable")
    void create() {
        Unlockable unlockable = new Unlockable();
        unlockable.setStatus(1);

        Unlockable createdUnlockable = new Unlockable();
        createdUnlockable.setIdUnlockable(1L);

        when(unlockableRepository.save(any())).thenReturn(createdUnlockable);

        Unlockable result = unlockableService.create(unlockable);

        assertEquals(result.getIdUnlockable(), createdUnlockable.getIdUnlockable());
    }

    @Test
    @DisplayName("When create With Valid Attributes And With An Id Then Returns IllegalArgumentException")
    void createIllegalArgumentException() throws  IllegalArgumentException{
        String expectedMessage = "Body should not contain id";
        Unlockable unlockable = new Unlockable();
        unlockable.setStatus(1);
        unlockable.setIdUnlockable(1L);

        Throwable exception = Assertions.catchThrowable( () -> {
            Unlockable result = unlockableService.create(unlockable);
        });

        Assertions.assertThat(exception).isInstanceOf(IllegalArgumentException.class).hasMessage(expectedMessage);
    }


    @Test
    @DisplayName("When findById With Valid Id Then Returns Unlockable")
    void findById() {
        Unlockable unlockable = new Unlockable();
        unlockable.setStatus(1);
        unlockable.setIdUnlockable(1L);

        when(unlockableRepository.findById(1L)).thenReturn(Optional.of(unlockable));

        final Unlockable result = unlockableService.findById(1L);

        assertEquals(result.getIdUnlockable(), unlockable.getIdUnlockable());
    }
    @Test
    @DisplayName("When findById With Invalid Id Then Returns Null")
    void findByIdNull() {
        assertNull(unlockableService.findById(1L));
    }


    @Test
    @DisplayName("When findAll Then Returns Unlockable List")
    void findAll() {
        Unlockable unlockable = new Unlockable();
        unlockable.setStatus(1);

        Unlockable unlockable2 = new Unlockable();
        unlockable2.setStatus(2);

        List<Unlockable> unlockableList = new ArrayList<>();
        unlockableList.add(unlockable);
        unlockableList.add(unlockable2);

        when(unlockableRepository.findAll()).thenReturn( unlockableList);

        List<Unlockable> result = unlockableService.findAll();

        assertEquals(result.size(), 2);
    }

    @Test
    @DisplayName("When update With Valid Attributes And Valid Id Then Returns Unlockable")
    void update() throws IllegalAccessException {
        Unlockable unlockable = new Unlockable();
        unlockable.setStatus(1);

        Unlockable retrievedUnlockable = new Unlockable();
        retrievedUnlockable.setIdUnlockable(4L);
        retrievedUnlockable.setStatus(2);

        when(unlockableRepository.findById(any())).thenReturn(Optional.of(retrievedUnlockable));
        when(unlockableRepository.save(any())).thenReturn(retrievedUnlockable);

        final Unlockable result = unlockableService.update(unlockable);


        assertEquals(retrievedUnlockable.getIdUnlockable(), result.getIdUnlockable());
        assertEquals(retrievedUnlockable.getStatus(), result.getStatus());
    }

    @Test
    @DisplayName("When update With Valid Attributes And Invalid Id Then Returns NoSuchElementException")
    void updateNoSuchElementException() throws IllegalAccessException {
        String expectedMessage = "Element does not exist in database";
        Unlockable retrievedUnlockable = new Unlockable();
        retrievedUnlockable.setIdUnlockable(4L);
        retrievedUnlockable.setStatus(1);

        Throwable exception = Assertions.catchThrowable( () -> {
            Unlockable result = unlockableService.update(retrievedUnlockable);
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }



    @Test
    @DisplayName("When change With Valid Attributes And Valid Id Then Returns Unlockable")
    void change() throws IllegalAccessException {
        Unlockable unlockable = new Unlockable();
        unlockable.setStatus(1);

        Unlockable retrievedUnlockable = new Unlockable();
        retrievedUnlockable.setIdUnlockable(4L);
        retrievedUnlockable.setStatus(2);

        when(unlockableRepository.findById(any())).thenReturn(Optional.of(retrievedUnlockable));
        when(unlockableRepository.save(any())).thenReturn(retrievedUnlockable);

        final Unlockable result = unlockableService.change(unlockable);

        assertEquals(retrievedUnlockable.getIdUnlockable(), result.getIdUnlockable());
        assertEquals(retrievedUnlockable.getStatus(), result.getStatus());
    }

    @Test
    @DisplayName("When change With Valid Attributes And Invalid Id Then Returns NoSuchElementException")
    void changeNoSuchElementException() throws IllegalAccessException {
        String expectedMessage = "Element does not exist in database";
        Unlockable retrievedUnlockable = new Unlockable();
        retrievedUnlockable.setIdUnlockable(4L);
        retrievedUnlockable.setStatus(1);

        Throwable exception = Assertions.catchThrowable( () -> {
            Unlockable result = unlockableService.change(retrievedUnlockable);
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When removeById With Valid Id Then Returns Unlockable")
    void removeById(){
        Unlockable retrievedUnlockable = new Unlockable();
        retrievedUnlockable.setIdUnlockable(4L);
        retrievedUnlockable.setStatus(2);

        when(unlockableRepository.findById(4L)).thenReturn(Optional.of(retrievedUnlockable));

        unlockableService.removeById(4L);
        verify(unlockableRepository).deleteById(4L);
    }


    @Test
    @DisplayName("When removeById Invalid Id Then Returns NoSuchElementException")
    void removeByIdNoSuchElementException() {
        String expectedMessage = "Element does not exist in database";

        Throwable exception = Assertions.catchThrowable( () -> {
            Unlockable result = unlockableService.removeById(4L);
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When getPossibleStatus Then Returns Unlockable List")
    void getPossibleStatus() {
        UnlockableStatusConstants unlockableStatusConstants = UnlockableStatusConstants.DELETED;
        UnlockableStatusConstants unlockableStatusConstants2 = UnlockableStatusConstants.ACTIVE;

        List<UnlockableStatusConstants> unlockableStatusConstantsList = new ArrayList<>();
        unlockableStatusConstantsList.add(unlockableStatusConstants);
        unlockableStatusConstantsList.add(unlockableStatusConstants2);

        when(unlockableServiceMock.getPossibleStatus()).thenReturn(Arrays.stream(UnlockableStatusConstants.values()).collect(Collectors.toList()));

        List<UnlockableStatusConstants> result = unlockableService.getPossibleStatus();

        assertEquals(result, unlockableStatusConstantsList);
    }

    @Test
    @DisplayName("When getPossibleUnlockerTypes Then Returns Unlockable List")
    void getPossibleUnlockerTypes() {
        UnlockerTypeConstants unlockerTypeConstants = UnlockerTypeConstants.RANK;
        UnlockerTypeConstants unlockerTypeConstants2 = UnlockerTypeConstants.ACCURACY;
        UnlockerTypeConstants unlockerTypeConstants3 = UnlockerTypeConstants.CONCERTS;
        UnlockerTypeConstants unlockerTypeConstants4 = UnlockerTypeConstants.POINTS;

        List<UnlockerTypeConstants> unlockerTypeConstantsArrayList = new ArrayList<>();
        unlockerTypeConstantsArrayList.add(unlockerTypeConstants);
        unlockerTypeConstantsArrayList.add(unlockerTypeConstants4);
        unlockerTypeConstantsArrayList.add(unlockerTypeConstants3);
        unlockerTypeConstantsArrayList.add(unlockerTypeConstants2);

        when(unlockableServiceMock.getPossibleUnlockerTypes()).thenReturn(Arrays.stream(UnlockerTypeConstants.values()).collect(Collectors.toList()));

        List<UnlockerTypeConstants> result = unlockableService.getPossibleUnlockerTypes();

        assertEquals(result, unlockerTypeConstantsArrayList);
    }

    @Test
    @DisplayName("When getPossibleRareness Then Returns Unlockable List")
    void getPossibleRareness() {
        UnlockableRarenessConstants unlockableRarenessConstants = UnlockableRarenessConstants.PLATINUM;
        UnlockableRarenessConstants unlockableRarenessConstants2 = UnlockableRarenessConstants.BRONZE;
        UnlockableRarenessConstants unlockableRarenessConstants3 = UnlockableRarenessConstants.GOLD;
        UnlockableRarenessConstants unlockableRarenessConstants4 = UnlockableRarenessConstants.SILVER;
        UnlockableRarenessConstants unlockableRarenessConstants5 = UnlockableRarenessConstants.WOOD;

        List<UnlockableRarenessConstants> unlockableRarenessConstantsList = new ArrayList<>();
        unlockableRarenessConstantsList.add(unlockableRarenessConstants5);
        unlockableRarenessConstantsList.add(unlockableRarenessConstants2);
        unlockableRarenessConstantsList.add(unlockableRarenessConstants4);
        unlockableRarenessConstantsList.add(unlockableRarenessConstants3);
        unlockableRarenessConstantsList.add(unlockableRarenessConstants);

        when(unlockableServiceMock.getPossibleRareness()).thenReturn(Arrays.stream(UnlockableRarenessConstants.values()).collect(Collectors.toList()));

        List<UnlockableRarenessConstants> result = unlockableService.getPossibleRareness();

        assertEquals(result, unlockableRarenessConstantsList);
    }
}
