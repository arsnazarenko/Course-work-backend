package com.itmo.squid.controller;

import com.itmo.squid.domain.Death;
import com.itmo.squid.domain.Participant;
import com.itmo.squid.domain.Stage;
import com.itmo.squid.domain.StageStatus;
import com.itmo.squid.dto.KillRespDto;
import com.itmo.squid.dto.MappingUtils;
import com.itmo.squid.dto.ParticipantRespDto;
import com.itmo.squid.exception.BadRequestException;
import com.itmo.squid.exception.ResourceNotFoundException;
import com.itmo.squid.repo.DeathRepo;
import com.itmo.squid.repo.ParticipantRepo;
import com.itmo.squid.repo.StageRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "participant")
public class ParticipantController {

    private final ParticipantRepo participantRepo;
    private final StageRepo stageRepo;
    private final DeathRepo deathRepo;

    public ParticipantController(ParticipantRepo participantRepo, StageRepo stageRepo, DeathRepo deathRepo) {
        this.participantRepo = participantRepo;
        this.stageRepo = stageRepo;
        this.deathRepo = deathRepo;
    }


    @GetMapping
    public List<ParticipantRespDto> list() {
        return participantRepo.findAll().stream().map(MappingUtils::fromParticipantEntityToDto).collect(Collectors.toList());
    }


    @GetMapping("{id}")
    public ParticipantRespDto getOne(@PathVariable Long id) {
        return participantRepo.findById(id).map(MappingUtils::fromParticipantEntityToDto).orElseThrow(ResourceNotFoundException::new);
    }


    @PutMapping("{id}/kill")
    public KillRespDto kill(@PathVariable Long id, @RequestBody Map<String, String> description) {
        Stage stage = stageRepo.findStageByStatusEquals(StageStatus.CONTINUOUS).orElseThrow(ResourceNotFoundException::new);
        String descr = Optional.ofNullable(description.get("description")).orElseThrow(BadRequestException::new);
        Participant participant = participantRepo.findParticipantByIdAndIsAlive(id, true).orElseThrow(BadRequestException::new);

        participant.setAlive(false);
        stage.setAmountOfDeath(stage.getAmountOfDeath() + 1);
        deathRepo.save(new Death(null, participant, stage, descr));
        stageRepo.save(stage);
        ParticipantRespDto participantRespDto = MappingUtils.fromParticipantEntityToDto(participantRepo.save(participant));
        return new KillRespDto(participantRespDto, stage.getId());
    }




}
