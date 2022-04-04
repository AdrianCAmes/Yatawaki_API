package com.orchestrator.orchestrator.model.dto.concert.response;

import com.orchestrator.orchestrator.model.Rank;
import com.orchestrator.orchestrator.model.Unlockable;
import lombok.Data;

import java.util.List;

@Data
public class ConcertCompleteResponseDto {
    Rank newRank;
    List<Unlockable> unlockedObjects;
}
