package com.orchestrator.orchestrator.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "[User_Unlockable]", schema = "[Orchestrator]")
@Data
public class UserUnlockable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUserUnlockable;
    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "idUnlockable", nullable = false)
    private Unlockable unlockable;
    private LocalDate unlockedDate;
    private Integer status;
}
