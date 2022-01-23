package com.itmo.squid.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "participant")
@Data
@EqualsAndHashCode(of = {"id"})
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull
    @Column
    private String name;

    @NotNull
    @Column
    private String job;

    @NotNull
    @Column
    private Date dateOfBirth;

    @Column
    @NotNull
    private Long debt;


    @Column
    @NotNull
    private boolean isAlive;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "participant_in_team",
            joinColumns = @JoinColumn(name = "participant_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private Set<TeamOnStage> teams = new HashSet<>();

    @OneToMany(mappedBy = "participant", fetch = FetchType.LAZY)
    private Set<Bet> bets = new HashSet<>();

    @OneToMany(mappedBy = "participant", fetch = FetchType.LAZY)
    private Set<MarketGood> goods = new HashSet<>();



}
