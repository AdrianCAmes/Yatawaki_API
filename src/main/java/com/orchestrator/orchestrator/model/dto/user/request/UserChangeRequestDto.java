package com.orchestrator.orchestrator.model.dto.user.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserChangeRequestDto {
    private Long idUser;
    private Long idUserStatistics;
    private String nickname;
    private String password;
    private String firstname;
    private String lastname;
    private String mail;
    private LocalDate birthDate;
    private Integer coinsOwned;
    private Integer status;
    private String role;
    private Boolean showTutorials;
}
