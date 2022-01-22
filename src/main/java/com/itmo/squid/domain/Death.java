package com.itmo.squid.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@Table(name = "death")
@Data
@EqualsAndHashCode(of = {"id"})
public class Death {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "participant_id", nullable = false)
    private Participant participant;

    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="stage_id", nullable = false)
    private Stage stage;

    @Column
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private ReasonType reason;

    @Column
    @NotNull
    private String description;


    enum ReasonType {
        FIGHT, SUICIDE, STAGE
    }
}
