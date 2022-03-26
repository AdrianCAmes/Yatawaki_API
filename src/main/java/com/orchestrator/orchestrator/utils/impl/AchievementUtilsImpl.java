package com.orchestrator.orchestrator.utils.impl;

import com.orchestrator.orchestrator.model.Achievement;
import com.orchestrator.orchestrator.model.dto.achievement.request.AchievementChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.achievement.request.AchievementCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.achievement.request.AchievementUpdateRequestDto;
import com.orchestrator.orchestrator.utils.AchievementUtils;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import com.orchestrator.orchestrator.utils.constants.UnlockableStatusConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AchievementUtilsImpl implements AchievementUtils {
    private final GeneralUtils generalUtils;

    @Override
    public Achievement buildDomainFromCreateRequestDto(AchievementCreateRequestDto achievementCreateRequestDto) throws IllegalAccessException {
        Achievement achievement = new Achievement();
        achievement.setStatus(UnlockableStatusConstants.ACTIVE.getValue());
        generalUtils.mapFields(achievementCreateRequestDto, achievement);
        return achievement;
    }

    @Override
    public Achievement buildDomainFromUpdateRequestDto(AchievementUpdateRequestDto achievementUpdateRequestDto) throws IllegalAccessException {
        Achievement achievement = new Achievement();
        generalUtils.mapFields(achievementUpdateRequestDto, achievement);
        return achievement;
    }

    @Override
    public Achievement buildDomainFromChangeRequestDto(AchievementChangeRequestDto achievementChangeRequestDto) throws IllegalAccessException {
        Achievement achievement = new Achievement();
        generalUtils.mapFields(achievementChangeRequestDto, achievement);
        return achievement;
    }

    @Override
    public AchievementCreateRequestDto buildCreateRequestDtoFromDomain(Achievement achievement) throws IllegalAccessException {
        AchievementCreateRequestDto achievementCreateRequestDto = new AchievementCreateRequestDto();
        generalUtils.mapFields(achievement, achievementCreateRequestDto);
        return achievementCreateRequestDto;
    }

    @Override
    public AchievementUpdateRequestDto buildUpdateRequestDtoFromDomain(Achievement achievement) throws IllegalAccessException {
        AchievementUpdateRequestDto achievementUpdateRequestDto = new AchievementUpdateRequestDto();
        generalUtils.mapFields(achievement, achievementUpdateRequestDto);
        return achievementUpdateRequestDto;
    }

    @Override
    public AchievementChangeRequestDto buildChangeRequestDtoFromDomain(Achievement achievement) throws IllegalAccessException {
        AchievementChangeRequestDto achievementChangeRequestDto = new AchievementChangeRequestDto();
        generalUtils.mapFields(achievement, achievementChangeRequestDto);
        return achievementChangeRequestDto;
    }
}
