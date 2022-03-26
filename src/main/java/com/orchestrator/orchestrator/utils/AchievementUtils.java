package com.orchestrator.orchestrator.utils;

import com.orchestrator.orchestrator.model.Achievement;
import com.orchestrator.orchestrator.model.dto.achievement.request.AchievementChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.achievement.request.AchievementCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.achievement.request.AchievementUpdateRequestDto;

public interface AchievementUtils {
    Achievement buildDomainFromCreateRequestDto(AchievementCreateRequestDto achievementCreateRequestDto) throws IllegalAccessException;
    Achievement buildDomainFromUpdateRequestDto(AchievementUpdateRequestDto achievementUpdateRequestDto) throws IllegalAccessException;
    Achievement buildDomainFromChangeRequestDto(AchievementChangeRequestDto achievementChangeRequestDto) throws IllegalAccessException;
    AchievementCreateRequestDto buildCreateRequestDtoFromDomain(Achievement achievement) throws IllegalAccessException;
    AchievementUpdateRequestDto buildUpdateRequestDtoFromDomain(Achievement achievement) throws IllegalAccessException;
    AchievementChangeRequestDto buildChangeRequestDtoFromDomain(Achievement achievement) throws IllegalAccessException;
}
