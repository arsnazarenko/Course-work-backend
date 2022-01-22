package com.itmo.squid.domain;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Set;

//@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
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
    @Min(0)
    private Integer amountOfDeath;

    @Column
    @NotNull
    private Boolean teamStage;

    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="status_id", nullable = false)
    private StageStatus status;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "attribute_on_stage",
        joinColumns = @JoinColumn(name = "stage_id"),
        inverseJoinColumns = @JoinColumn(name = "attribute_id")
    )
    private Set<Attribute> attributes;


    @OneToMany(mappedBy = "stage", fetch = FetchType.LAZY)
    private Set<TeamOnStage> teams;

    @OneToMany(mappedBy = "vip", fetch = FetchType.LAZY)
    private Set<Bet> bets;

}
