package com.itmo.squid.domain;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Entity
@Table(name = "market_good")
@Data
@EqualsAndHashCode(of = {"id"})
public class MarketGood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="participant_id", nullable = false)
    private Participant participant;

    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="organ_id", nullable = false)
    private OrganRate organ;

    @NotNull
    @Column
    private boolean on_sale;
}
