package com.itmo.squid.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.itmo.squid.domain.ReqType;
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
public class ParticipantRequestDtoResponse {


    @NotNull
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String job;

    @NotNull
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date dateOfBirth;

    @NotNull
    private Long debt;

    @NotNull
    private Long userId;

    @NotNull
    private ReqType reqType;

}