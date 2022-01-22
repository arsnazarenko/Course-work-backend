package com.itmo.squid.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

//@JsonIgnoreProperties({"hibernateLazyInitializer"})
@NoArgsConstructor
@Entity
@Table(name = "stage_status")
@Data
@EqualsAndHashCode(of = {"id"})
public class StageStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private StatusType status;

    @OneToMany(mappedBy = "status", fetch = FetchType.LAZY)
    private Set<Stage> stages;



    enum StatusType {
        CONTINUOUS, END, NOT_OPEN
    }
}
