package com.itmo.squid.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KillRespDto {
    @NotNull
    private ParticipantRespDto participant;

    @NotNull
    private Long onStageId;
}
