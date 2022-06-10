package com.orchestrator.orchestrator.business.impl;

import com.orchestrator.orchestrator.business.RankService;
import com.orchestrator.orchestrator.model.Rank;
import com.orchestrator.orchestrator.repository.RankRepository;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import com.orchestrator.orchestrator.utils.constants.RankStatusConstants;
import com.orchestrator.orchestrator.utils.constants.UserStatisticsStatusConstants;
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

public class RankServiceImplTest {

    @Mock
    private RankRepository rankRepository;

    private GeneralUtils generalUtils;
    private RankService rankService;

    @Mock
    private RankService rankServiceMock = mock(RankServiceImpl.class);

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        generalUtils = new GeneralUtilsImpl();
        rankService = new RankServiceImpl(rankRepository, generalUtils) {
        };
    }

    @Test
    @DisplayName("When create With Valid Attributes And Without An Id Then Returns Rank")
    void create() {
        Rank rank = new Rank();
        rank.setName("My name");

        Rank createdRank = new Rank();
        createdRank.setIdRank(1L);

        when(rankRepository.save(any())).thenReturn(createdRank);

        Rank result = rankService.create(rank);

        assertEquals(result.getIdRank(), createdRank.getIdRank());
    }

    @Test
    @DisplayName("When create With Valid Attributes And With An Id Then Returns IllegalArgumentException")
    void createIllegalArgumentException() throws  IllegalArgumentException{
        String expectedMessage = "Body should not contain id";
        Rank rank = new Rank();
        rank.setName("My name");
        rank.setIdRank(1L);

        Throwable exception = Assertions.catchThrowable( () -> {
            Rank result = rankService.create(rank);
        });

        Assertions.assertThat(exception).isInstanceOf(IllegalArgumentException.class).hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When findById With Valid Id Then Returns Rank")
    void findById() {
        Rank rank = new Rank();
        rank.setName("My name");

        when(rankRepository.findById(1L)).thenReturn(Optional.ofNullable(rank));

        final Rank result = rankService.findById(1L);

        assertEquals(result.toString(), rank.toString());
    }

    @Test
    @DisplayName("When findById With Invalid Id Then Returns Null")
    void findByIdNull() {
        assertNull(rankService.findById(1L));
    }


    @Test
    @DisplayName("When findAll Then Returns Rank List")
    void findAll() {
        Rank rank = new Rank();
        rank.setName("My name");

        Rank rank2 = new Rank();
        rank2.setName("My name 2");

        List<Rank> rankList = new ArrayList<>();
        rankList.add(rank);
        rankList.add(rank2);

        when(rankRepository.findAll()).thenReturn( rankList);

        List<Rank> result = rankService.findAll();

        assertEquals(result.size(), 2);
    }

    @Test
    @DisplayName("When update With Valid Attributes And Valid Id Then Returns Rank")
    void update() throws IllegalAccessException {
        Rank rank = new Rank();
        rank.setName("New Name");

        Rank retrievedRank = new Rank();
        retrievedRank.setIdRank(4L);
        retrievedRank.setName("Old Name");

        when(rankRepository.findById(any())).thenReturn(Optional.of(retrievedRank));
        when(rankRepository.save(any())).thenReturn(retrievedRank);

        final Rank result = rankService.update(rank);

        assertEquals(retrievedRank.toString(), result.toString());
    }

    @Test
    @DisplayName("When update With Valid Attributes And Invalid Id Then Returns NoSuchElementException")
    void updateNoSuchElementException() throws IllegalAccessException {
        String expectedMessage = "Element does not exist in database";
        Rank retrievedRank = new Rank();
        retrievedRank.setIdRank(4L);
        retrievedRank.setName("Name");

        Throwable exception = Assertions.catchThrowable( () -> {
            Rank result = rankService.update(retrievedRank);
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }



    @Test
    @DisplayName("When change With Valid Attributes And Valid Id Then Returns Rank")
    void change() throws IllegalAccessException {
        Rank rank = new Rank();
        rank.setName("New Name");

        Rank retrievedRank = new Rank();
        retrievedRank.setIdRank(4L);
        retrievedRank.setName("Old Name");

        when(rankRepository.findById(any())).thenReturn(Optional.of(retrievedRank));
        when(rankRepository.save(any())).thenReturn(retrievedRank);

        final Rank result = rankService.change(rank);

        assertEquals(retrievedRank.toString(), result.toString());
    }

    @Test
    @DisplayName("When change With Valid Attributes And Invalid Id Then Returns NoSuchElementException")
    void changeNoSuchElementException() throws IllegalAccessException {
        String expectedMessage = "Element does not exist in database";
        Rank retrievedRank = new Rank();
        retrievedRank.setIdRank(4L);
        retrievedRank.setName("Name");

        Throwable exception = Assertions.catchThrowable( () -> {
            Rank result = rankService.change(retrievedRank);
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When removeById With Valid Id Then Returns Rank")
    void removeById(){
        Rank retrievedRank = new Rank();
        retrievedRank.setIdRank(4L);
        retrievedRank.setName("Name");

        when(rankRepository.findById(4L)).thenReturn(Optional.of(retrievedRank));

        rankService.removeById(4L);
        verify(rankRepository).deleteById(4L);
    }


    @Test
    @DisplayName("When removeById Invalid Id Then Returns NoSuchElementException")
    void removeByIdNoSuchElementException() {
        String expectedMessage = "Element does not exist in database";

        Throwable exception = Assertions.catchThrowable( () -> {
            Rank result = rankService.removeById(4L);
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When getPossibleStatus Then Returns Rank List")
    void getPossibleStatus() {
        RankStatusConstants rankStatusConstants = RankStatusConstants.DELETED;
        RankStatusConstants rankStatusConstants2 = RankStatusConstants.ACTIVE;

        List<RankStatusConstants> rankStatusConstantsList = new ArrayList<>();
        rankStatusConstantsList.add(rankStatusConstants);
        rankStatusConstantsList.add(rankStatusConstants2);

        when(rankServiceMock.getPossibleStatus()).thenReturn(Arrays.stream(RankStatusConstants.values()).collect(Collectors.toList()));

        List<RankStatusConstants> result = rankService.getPossibleStatus();

        assertEquals(result, rankStatusConstantsList);
    }
}
