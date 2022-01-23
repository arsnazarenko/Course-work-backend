package com.itmo.squid.domain;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.servlet.http.HttpSessionActivationListener;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

//@JsonIgnoreProperties({"hibernateLazyInitializer"})
@NoArgsConstructor
@Entity
@Table(name = "vip")
@Data
@EqualsAndHashCode(of = {"id"})
public class Vip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column
    private Long capital;

    @NotNull
    @Column
    private String name;


    @OneToMany(mappedBy = "vip", fetch = FetchType.EAGER)
    private Set<Bet> bets = new HashSet<>();


}
