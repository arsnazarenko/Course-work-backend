package com.itmo.squid.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantReqDto {


    @NotNull
    private String name;

    @NotNull
    private String job;

    @NotNull
    private Date dateOfBirth;

    @Min(1)
    @NotNull
    private Long debt;

}
