package com.itmo.squid.controller;

import com.itmo.squid.domain.Death;
import com.itmo.squid.domain.Participant;
import com.itmo.squid.domain.Stage;
import com.itmo.squid.domain.StageStatus;
import com.itmo.squid.dto.MappingUtils;
import com.itmo.squid.dto.ParticipantRespDto;
import com.itmo.squid.exception.BadRequestException;
import com.itmo.squid.exception.ResourceNotFoundException;
import com.itmo.squid.repo.DeathRepo;
import com.itmo.squid.repo.ParticipantRepo;
import com.itmo.squid.repo.StageRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
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


    @GetMapping("{id}/kill")
    public ParticipantRespDto kill(@PathVariable Long id, @RequestBody Map<String, String> description) {
        Stage stage = stageRepo.findStageByStatusEquals(StageStatus.CONTINUOUS).orElseThrow(ResourceNotFoundException::new);
        String descr = Optional.ofNullable(description.get("description")).orElseThrow(BadRequestException::new);
        Participant participant = participantRepo.findParticipantByIdAndAlive(id, true).orElseThrow(BadRequestException::new);

        participant.setAlive(false);
        stage.setAmountOfDeath(stage.getAmountOfDeath() + 1);
        deathRepo.save(new Death(null, participant, stage, descr));
        stageRepo.save(stage);
        return MappingUtils.fromParticipantEntityToDto(participantRepo.save(participant));
    }




}
