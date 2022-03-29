package com.orchestrator.orchestrator.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "[User]", schema = "[Orchestrator]")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idUserStatistics", referencedColumnName = "idUserStatistics")
    private UserStatistics userStatistics;
    @Column(unique = true)
    private String nickname;
    private String password;
    private String firstname;
    private String lastname;
    @Column(unique = true)
    private String mail;
    private LocalDate birthDate;
    private Integer currencyOwned;
    private Integer status;
    private String role;

}
