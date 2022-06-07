package com.orchestrator.orchestrator.business.impl;

import com.orchestrator.orchestrator.business.UserRankService;
import com.orchestrator.orchestrator.model.UserRank;
import com.orchestrator.orchestrator.repository.UserRankRepository;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import com.orchestrator.orchestrator.utils.constants.UserRankStatusConstants;
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

public class UserRankServiceImplTest {

    @Mock
    private UserRankRepository userRankRepository;

    private GeneralUtils generalUtils;
    private UserRankService userRankService;

    @Mock
    private UserRankService userRankServiceMock = mock(UserRankServiceImpl.class);

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        generalUtils = new GeneralUtilsImpl();
        userRankService = new UserRankServiceImpl(userRankRepository, generalUtils) {
        };
    }

    @Test
    @DisplayName("When create With Valid Attributes And Without An Id Then Returns UserRank")
    void create() {
        UserRank userRank = new UserRank();
        userRank.setStatus(1);

        UserRank createdUserRank = new UserRank();
        createdUserRank.setIdUserRank(1L);

        when(userRankRepository.save(any())).thenReturn(createdUserRank);

        UserRank result = userRankService.create(userRank);

        assertEquals(result.getIdUserRank(), createdUserRank.getIdUserRank());
    }

    @Test
    @DisplayName("When create With Valid Attributes And With An Id Then Returns IllegalArgumentException")
    void createIllegalArgumentException() throws  IllegalArgumentException{
        String expectedMessage = "Body should not contain id";
        UserRank userRank = new UserRank();
        userRank.setStatus(1);
        userRank.setIdUserRank(1L);

        Throwable exception = Assertions.catchThrowable( () -> {
            UserRank result = userRankService.create(userRank);
        });

        Assertions.assertThat(exception).isInstanceOf(IllegalArgumentException.class).hasMessage(expectedMessage);
    }


    @Test
    @DisplayName("When findById With Valid Id Then Returns UserRank")
    void findById() {
        UserRank userRank = new UserRank();
        userRank.setStatus(1);
        userRank.setIdUserRank(1L);

        when(userRankRepository.findById(1L)).thenReturn(Optional.of(userRank));

        final UserRank result = userRankService.findById(1L);

        assertEquals(result.getIdUserRank(), userRank.getIdUserRank());
    }
    @Test
    @DisplayName("When findById With Invalid Id Then Returns Null")
    void findByIdNull() {
        assertNull(userRankService.findById(1L));
    }


    @Test
    @DisplayName("When findAll Then Returns UserRank List")
    void findAll() {
        UserRank userRank = new UserRank();
        userRank.setStatus(1);

        UserRank userRank2 = new UserRank();
        userRank2.setStatus(2);

        List<UserRank> userRankList = new ArrayList<>();
        userRankList.add(userRank);
        userRankList.add(userRank2);

        when(userRankRepository.findAll()).thenReturn( userRankList);

        List<UserRank> result = userRankService.findAll();

        assertEquals(result.size(), 2);
    }

    @Test
    @DisplayName("When update With Valid Attributes And Valid Id Then Returns UserRank")
    void update() throws IllegalAccessException {
        UserRank userRank = new UserRank();
        userRank.setStatus(1);

        UserRank retrievedUserRank = new UserRank();
        retrievedUserRank.setIdUserRank(4L);
        retrievedUserRank.setStatus(2);

        when(userRankRepository.findById(any())).thenReturn(Optional.of(retrievedUserRank));
        when(userRankRepository.save(any())).thenReturn(retrievedUserRank);

        final UserRank result = userRankService.update(userRank);


        assertEquals(retrievedUserRank.getIdUserRank(), result.getIdUserRank());
        assertEquals(retrievedUserRank.getStatus(), result.getStatus());
    }

    @Test
    @DisplayName("When update With Valid Attributes And Invalid Id Then Returns NoSuchElementException")
    void updateNoSuchElementException() throws IllegalAccessException {
        String expectedMessage = "Element does not exist in database";
        UserRank retrievedUserRank = new UserRank();
        retrievedUserRank.setIdUserRank(4L);
        retrievedUserRank.setStatus(1);

        Throwable exception = Assertions.catchThrowable( () -> {
            UserRank result = userRankService.update(retrievedUserRank);
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }



    @Test
    @DisplayName("When change With Valid Attributes And Valid Id Then Returns UserRank")
    void change() throws IllegalAccessException {
        UserRank userRank = new UserRank();
        userRank.setStatus(1);

        UserRank retrievedUserRank = new UserRank();
        retrievedUserRank.setIdUserRank(4L);
        retrievedUserRank.setStatus(2);

        when(userRankRepository.findById(any())).thenReturn(Optional.of(retrievedUserRank));
        when(userRankRepository.save(any())).thenReturn(retrievedUserRank);

        final UserRank result = userRankService.change(userRank);

        assertEquals(retrievedUserRank.getIdUserRank(), result.getIdUserRank());
        assertEquals(retrievedUserRank.getStatus(), result.getStatus());
    }

    @Test
    @DisplayName("When change With Valid Attributes And Invalid Id Then Returns NoSuchElementException")
    void changeNoSuchElementException() throws IllegalAccessException {
        String expectedMessage = "Element does not exist in database";
        UserRank retrievedUserRank = new UserRank();
        retrievedUserRank.setIdUserRank(4L);
        retrievedUserRank.setStatus(1);

        Throwable exception = Assertions.catchThrowable( () -> {
            UserRank result = userRankService.change(retrievedUserRank);
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When removeById With Valid Id Then Returns UserRank")
    void removeById(){
        UserRank retrievedUserRank = new UserRank();
        retrievedUserRank.setIdUserRank(4L);
        retrievedUserRank.setStatus(2);

        when(userRankRepository.findById(4L)).thenReturn(Optional.of(retrievedUserRank));

        userRankService.removeById(4L);
        verify(userRankRepository).deleteById(4L);
    }


    @Test
    @DisplayName("When removeById Invalid Id Then Returns NoSuchElementException")
    void removeByIdNoSuchElementException() {
        String expectedMessage = "Element does not exist in database";

        Throwable exception = Assertions.catchThrowable( () -> {
            UserRank result = userRankService.removeById(4L);
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When getPossibleStatus Then Returns UserRank List")
    void getPossibleStatus() {
        UserRankStatusConstants userRankStatusConstants = UserRankStatusConstants.DELETED;
        UserRankStatusConstants userRankStatusConstants2 = UserRankStatusConstants.ACTIVE;
        UserRankStatusConstants userRankStatusConstants3 = UserRankStatusConstants.FINISHED;

        List<UserRankStatusConstants> userRankStatusConstantsList = new ArrayList<>();
        userRankStatusConstantsList.add(userRankStatusConstants);
        userRankStatusConstantsList.add(userRankStatusConstants2);
        userRankStatusConstantsList.add(userRankStatusConstants3);

        when(userRankServiceMock.getPossibleStatus()).thenReturn(Arrays.stream(UserRankStatusConstants.values()).collect(Collectors.toList()));

        List<UserRankStatusConstants> result = userRankService.getPossibleStatus();

        assertEquals(result, userRankStatusConstantsList);
    }
}
