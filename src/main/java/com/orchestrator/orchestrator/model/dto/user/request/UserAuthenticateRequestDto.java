package com.orchestrator.orchestrator.model.dto.user.request;

import lombok.Data;

@Data
public class UserAuthenticateRequestDto {
    private String uniqueIdentifier;
    private String password;
}
