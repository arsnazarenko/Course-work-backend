package com.itmo.squid.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@ToString
@Entity
@NoArgsConstructor
@Table(name = "stage")
@Data
@EqualsAndHashCode(of = {"id"})
public class Stage {
    @Id
    @GeneratedValue(generator = "stage_id_seq")
    private Long id;

    @Column
    @NotNull
    @NotEmpty
    private String name;

    @Column
    @NotNull
    @NotEmpty
    private String description;

    @Column
    private Integer amountOfDeath;

    @Column
    @NotNull
    private Boolean teamStage;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name="status_id", nullable = false)
    private StageStatus status;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "attribute_on_stage",
        joinColumns = @JoinColumn(name = "stage_id"),
        inverseJoinColumns = @JoinColumn(name = "attribute_id")
    )
    private Set<Attribute> attributes;
}
