package com.itmo.squid.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ParticipantRespDto {
    @NotNull
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String job;

    @NotNull
    private Date dateOfBirth;

    @NotNull
    private Long debt;

    @NotNull
    private boolean isAlive;


}
