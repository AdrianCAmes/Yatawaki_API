package com.orchestrator.orchestrator.model.dto.user.response;

import com.orchestrator.orchestrator.model.Unlockable;
import com.orchestrator.orchestrator.model.UserRank;
import lombok.Data;

@Data
public class UserProfileResponseDto {
    private String firstname;
    private String lastname;
    private String mail;
    private String nickname;
    private Integer coinsOwned;
    private Integer notesOwned;
    private Unlockable avatar;
    private UserRank userRank;
}
