package com.itmo.squid.domain;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table(name = "organ_rate")
@Data
@EqualsAndHashCode(of = {"id"})
public class OrganRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    @Enumerated(value = EnumType.STRING)
    private OrganType organ;

    @NotNull
    @Column
    @Min(0)
    private Long price;

    @OneToMany(mappedBy = "organ", fetch = FetchType.LAZY)
    private Set<MarketGood> goods = new HashSet<>();


}
