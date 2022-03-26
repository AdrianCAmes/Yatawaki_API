package com.orchestrator.orchestrator.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "[User_Rank]", schema = "[Orchestrator]")
@Data
public class UserRank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUserRank;
    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "idRank", nullable = false)
    private Rank rank;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer currentExperience;
    private Integer status;
}
