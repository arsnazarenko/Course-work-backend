package com.itmo.squid.dto;

import com.itmo.squid.domain.TeamType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
    private TeamType type;


    private Set<Long> attributesIds;

}
