package com.itmo.squid.dto;

import com.itmo.squid.domain.StageStatus;
import com.itmo.squid.domain.TeamType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.Set;

@EqualsAndHashCode(of = {"id"})
@Data
@AllArgsConstructor
public class StageRespDto {

    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private TeamType type;
    @NotNull
    private StageStatus status;
    @NotNull
    private Set<Long> attributesIds;

    private Set<Set<Long>> teams;
}
