package com.orchestrator.orchestrator.model.dto.user.response;

import lombok.Data;

@Data
public class UserAuthenticateResponseDto {
    Boolean isAuthenticated;
    String jwt;
}
