package com.orchestrator.orchestrator.business.impl;

import com.orchestrator.orchestrator.business.RankService;
import com.orchestrator.orchestrator.model.Rank;
import com.orchestrator.orchestrator.repository.RankRepository;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class RankServiceImpl implements RankService {
    private final RankRepository rankRepository;
    private final GeneralUtils generalUtils;

    // region CRUD Operations
    @Override
    public Rank create(Rank rank) {
        if (rank.getIdRank() != null) throw new IllegalArgumentException("Body should not contain id");
        return rankRepository.save(rank);
    }

    @Override
    public Rank findById(Long id) {
        return rankRepository.findById(id).orElse(null);
    }

    @Override
    public List<Rank> findAll() {
        return rankRepository.findAll();
    }

    @Override
    public Rank change(Rank rank) {
        Rank rankToChange = findById(rank.getIdRank());
        if (rankToChange != null) {
            return rankRepository.save(rank);
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }

    @Override
    public Rank update(Rank rank) throws IllegalAccessException {
        Rank rankToUpdate = findById(rank.getIdRank());
        if (rankToUpdate != null) {
            generalUtils.mapFields(rank, rankToUpdate);
            return rankRepository.save(rankToUpdate);
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }

    @Override
    public Rank removeById(Long id) {
        Rank rankToDelete = findById(id);
        if (rankToDelete != null) {
            rankRepository.deleteById(id);
            return rankToDelete;
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }
    // endregion CRUD Operations

    // region Use Cases
    @Override
    public Rank findByName(String name) {
        return rankRepository.findByName(name);
    }
    // endregion Use Cases
}
