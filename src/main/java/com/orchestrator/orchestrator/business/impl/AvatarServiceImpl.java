package com.orchestrator.orchestrator.business.impl;

import com.orchestrator.orchestrator.business.AvatarService;
import com.orchestrator.orchestrator.model.Avatar;
import com.orchestrator.orchestrator.repository.AvatarRepository;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AvatarServiceImpl implements AvatarService {
    // Self repository
    private final AvatarRepository avatarRepository;
    // Utils
    private final GeneralUtils generalUtils;

    // region CRUD Operations
    @Override
    public Avatar create(Avatar avatar) {
        if (avatar.getIdUnlockable() != null) throw new IllegalArgumentException("Body should not contain id");
        return avatarRepository.save(avatar);
    }

    @Override
    public Avatar findById(Long id) {
        return avatarRepository.findById(id).orElse(null);
    }

    @Override
    public List<Avatar> findAll() {
        return avatarRepository.findAll();
    }

    @Override
    public Avatar change(Avatar avatar) {
        Avatar avatarToChange = findById(avatar.getIdUnlockable());
        if (avatarToChange != null) {
            return avatarRepository.save(avatar);
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }

    @Override
    public Avatar update(Avatar avatar) throws IllegalAccessException {
        Avatar avatarToUpdate = findById(avatar.getIdUnlockable());
        if (avatarToUpdate != null) {
            generalUtils.mapFields(avatar, avatarToUpdate);
            return avatarRepository.save(avatarToUpdate);
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }

    @Override
    public Avatar removeById(Long id) {
        Avatar avatarToDelete = findById(id);
        if (avatarToDelete != null) {
            avatarRepository.deleteById(id);
            return avatarToDelete;
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }
    // endregion CRUD Operations

    // region Use Cases External
    // endregion Use Cases External

    // region Use Cases Internal
    // endregion Use Cases Internal
}
