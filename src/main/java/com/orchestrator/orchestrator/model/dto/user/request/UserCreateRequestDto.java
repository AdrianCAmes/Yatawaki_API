package com.orchestrator.orchestrator.model.dto.user.request;

import lombok.Data;

@Data
public class UserCreateRequestDto {
    private Long idUserStatistics;
    private String nickname;
    private String name;
    private String lastname;
    private String mail;
}
