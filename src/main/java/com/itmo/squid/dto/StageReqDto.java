package com.itmo.squid.dto;

import com.itmo.squid.domain.TeamType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.util.Set;
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StageReqDto {
    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private Boolean isTeamStage;

    @NotNull
    private Set<Long> attributesIds;

}
