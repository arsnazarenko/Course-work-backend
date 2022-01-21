package com.itmo.squid.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


@JsonIgnoreProperties({"hibernateLazyInitializer"})
@NoArgsConstructor
@Entity
@Table(name = "human")
@Data
@EqualsAndHashCode(of = {"id"})
public class Human {
    @Id
    private Long id;

    @NotNull
    @Column
    private String name;

    @NotNull
    @Column
    private String job;

    @NotNull
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @OneToOne(optional = false, mappedBy = "human")
    private Vip vip;


}
