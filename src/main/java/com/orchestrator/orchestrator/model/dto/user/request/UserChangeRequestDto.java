package com.orchestrator.orchestrator.model.dto.user.request;

import lombok.Data;

@Data
public class UserChangeRequestDto {
    private Long idUser;
    private Long idUserStatistics;
    private String nickname;
    private String name;
    private String lastname;
    private String mail;
    private Integer currencyOwned;
    private Integer status;
}
