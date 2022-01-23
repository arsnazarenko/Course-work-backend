package com.itmo.squid.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "team_on_stage")
@Data
@EqualsAndHashCode(of = {"id"})
public class TeamOnStage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="stage_id", nullable = false)
    private Stage stage;

    @NotNull
    @Column
    @Enumerated(value = EnumType.STRING)
    private TeamType type;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "participant_in_team",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "participant_id")
    )
    private Set<Participant> participants = new HashSet<>();



}
