package com.orchestrator.orchestrator.model.dto.user.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserCreateRequestDto {
    private Long idUserStatistics;
    private String nickname;
    private String password;
    private String firstname;
    private String lastname;
    private String mail;
    private LocalDate birthDate;
    private String role;
}
