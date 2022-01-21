package com.itmo.squid.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "contraband")
@Data
@EqualsAndHashCode(of = {"id"})
public class Contraband {
    @Id
    @GeneratedValue(generator = "contraband_id_seq")
    private Long id;

    @Column
    @NotEmpty
    @NotNull
    private String object;
}
