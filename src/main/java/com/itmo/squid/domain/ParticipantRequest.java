package com.itmo.squid.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "participant_req")
@Data
@EqualsAndHashCode(of = {"id"})
public class ParticipantRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column
    private String name;

    @NotNull
    @Column
    private String job;

    @NotNull
    @Column
    private Date dateOfBirth;

    @Column
    @NotNull
    private Long debt;

    @Column
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private ReqType type;

    @OneToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}