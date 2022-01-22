package com.itmo.squid.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table(name = "participant")
@Data
@EqualsAndHashCode(of = {"id"})
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "human_id")
    Human human;


    @Column
    @NotNull
    private boolean isAlive;

    @Column
    @NotNull
    private Long debt;

    @OneToOne(optional = true)
    @JoinColumn(name = "contraband_id")
    private Contraband contraband;



    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "participant_in_team",
            joinColumns = @JoinColumn(name = "participant_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private Set<TeamOnStage> teams;

    @OneToMany(mappedBy = "participant", fetch = FetchType.LAZY)
    private Set<Bet> bets;

    @OneToMany(mappedBy = "participant", fetch = FetchType.LAZY)
    private Set<MarketGood> goods;



}
