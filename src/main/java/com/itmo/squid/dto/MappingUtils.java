package com.itmo.squid.dto;

import com.itmo.squid.domain.*;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.stream.Collectors;


@Service
public class MappingUtils {

    public static Stage fromStageDtoToEntity(StageReqDto stageReqDto) {
        return new Stage(null, stageReqDto.getName(), stageReqDto.getDescription(), 0,
                stageReqDto.getType(), StageStatus.NOT_OPEN, new HashSet<>(), new HashSet<>(), new HashSet<>());
    }

    public static StageRespDto fromStageEntityToDto(Stage stage) {
        return new StageRespDto(stage.getId(),
                stage.getName(),
                stage.getDescription(),
                stage.getType(),
                stage.getStatus(),
                stage.getAttributes().stream().map(Attribute::getId).collect(Collectors.toSet()),
                stage.getTeams().stream().map(t -> t.getParticipants().stream().map(Participant::getId).collect(Collectors.toSet())).collect(Collectors.toSet()));
    }

    public static Participant fromParticipantDtoToEntity(ParticipantReqDto participantReqDto) {
        return new Participant(null, participantReqDto.getName(), participantReqDto.getJob(),
                participantReqDto.getDateOfBirth(), participantReqDto.getDebt(), true,
                new HashSet<>(), new HashSet<>(), new HashSet<>());
    }

    public static ParticipantRespDto fromParticipantEntityToDto(Participant participantReqDto) {
        return new ParticipantRespDto(participantReqDto.getId(), participantReqDto.getName(), participantReqDto.getJob(),
                participantReqDto.getDateOfBirth(), participantReqDto.getDebt(), participantReqDto.isAlive());
    }



}
