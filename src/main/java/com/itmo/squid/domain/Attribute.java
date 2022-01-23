package com.itmo.squid.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "attribute")
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Attribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column
    private String name;

    @ManyToMany
    @JoinTable(name="attribute_on_stage",
        joinColumns = @JoinColumn(name = "attribute_id"),
        inverseJoinColumns = @JoinColumn(name = "stage_id"))
    private Set<Stage> stages = new HashSet<>();
}
