package com.itmo.squid.domain;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stage")
@Data
@EqualsAndHashCode(of = {"id"})
public class Stage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    private String description;

    @Column
    @NotNull
    @Min(0)
    private Integer amountOfDeath;

    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    private TeamType type;

    @Column
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private StageStatus status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "attribute_on_stage",
        joinColumns = @JoinColumn(name = "stage_id"),
        inverseJoinColumns = @JoinColumn(name = "attribute_id")
    )
    private Set<Attribute> attributes = new HashSet<>();

    @OneToMany(mappedBy = "stage", fetch = FetchType.EAGER)
    private Set<TeamOnStage> teams = new HashSet<>();

    @OneToMany(mappedBy = "vip", fetch = FetchType.EAGER)
    private Set<Bet> bets = new HashSet<>();

}
