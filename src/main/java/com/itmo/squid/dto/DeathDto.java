package com.itmo.squid.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeathDto {
    @NotNull
    private Long id;
    @NotNull
    private Long partId;
    @NotNull
    private Long stageId;
    @NotNull
    private String description;
}
