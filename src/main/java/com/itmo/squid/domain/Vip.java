package com.itmo.squid.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@NoArgsConstructor
@Entity
@Table(name = "vip")
@Data
@EqualsAndHashCode(of = {"id"})
public class Vip {

    @Id
    private Long id;

    @NotNull
    @Column
    private Long capital;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "human_id")
    private Human human;

}
