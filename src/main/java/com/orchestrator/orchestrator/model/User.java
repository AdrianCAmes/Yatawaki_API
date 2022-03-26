package com.orchestrator.orchestrator.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "[User]", schema = "[Orchestrator]")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idUserStatistics", referencedColumnName = "idUserStatistics")
    private UserStatistics userStatistics;
    @Column(unique = true)
    private String nickname;
    private String name;
    private String lastname;
    @Column(unique = true)
    private String mail;
    private Integer currencyOwned;
    private Integer status;
}
