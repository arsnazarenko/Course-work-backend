package com.itmo.squid.controller;


import com.itmo.squid.domain.Participant;
import com.itmo.squid.domain.ParticipantRequest;
import com.itmo.squid.domain.ReqType;
import com.itmo.squid.domain.User;
import com.itmo.squid.dto.*;
import com.itmo.squid.exception.BadRequestException;
import com.itmo.squid.exception.ResourceNotFoundException;
import com.itmo.squid.repo.ParticipantRepo;
import com.itmo.squid.repo.ParticipantReqRepo;
import com.itmo.squid.repo.UserRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin()
@RestController
@RequestMapping("participant_request")
public class ParticipantRequestController {

    private final ParticipantReqRepo participantRequestRepo;
    private final UserRepo userRepo;
    private final ParticipantRepo participantRepo;

    public ParticipantRequestController(ParticipantReqRepo participantRequestRepo, UserRepo userRepo, ParticipantRepo participantRepo) {
        this.participantRequestRepo = participantRequestRepo;
        this.userRepo = userRepo;
        this.participantRepo = participantRepo;
    }


    @GetMapping
    public List<ParticipantRequestDtoResponse> list() {
        return participantRequestRepo.findAllByTypeEquals(ReqType.NONE).stream().map(MappingUtils::fromParticipantRequestToPartReqDtoResponse).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public ParticipantRequestDtoResponse getOne(@PathVariable("id") Long id) {
        return participantRequestRepo.findById(id).map(MappingUtils::fromParticipantRequestToPartReqDtoResponse).orElseThrow(ResourceNotFoundException::new);
    }

    @GetMapping("user/{id}")
    public ParticipantRequestDtoResponse getOneByUser(@PathVariable("id") Long id) {
        User user = userRepo.findById(id).orElseThrow(BadRequestException::new);
        ParticipantRequest participantRequest = participantRequestRepo.findParticipantRequestByUser(user).orElseThrow(ResourceNotFoundException::new);
        return MappingUtils.fromParticipantRequestToPartReqDtoResponse(participantRequest);
    }


    @PostMapping
    public ParticipantRequestDtoResponse create(@RequestBody ParticipantRequestDto participant) {
        User user = userRepo.findById(participant.getUserId()).orElseThrow(BadRequestException::new);
        boolean present = participantRequestRepo.findParticipantRequestByUser(user).isPresent();
        if (present) {
            throw new BadRequestException();
        }
        ParticipantRequest participantRequest = MappingUtils.fromParticipantReqDtoToParticipantRequest(participant);
        participantRequest.setUser(user);
        return MappingUtils.fromParticipantRequestToPartReqDtoResponse(participantRequestRepo.save(participantRequest));
    }

    @PutMapping("{id}/accept")
    public ParticipantRespDto accept(@PathVariable("id") Long id) {
        ParticipantRequest participantRequest = participantRequestRepo.findById(id).orElseThrow(ResourceNotFoundException::new);
        participantRequest.setType(ReqType.ACCEPTED);
        participantRequestRepo.save(participantRequest);
        Participant save = participantRepo.save(MappingUtils.fromParticipantRequestToParticipant(participantRequest));
        return MappingUtils.fromParticipantEntityToDto(save);
    }

    @PutMapping("{id}/denied")
    public ParticipantRequestDtoResponse denied(@PathVariable("id") Long id) {
        ParticipantRequest participantRequest = participantRequestRepo.findById(id).orElseThrow(ResourceNotFoundException::new);
        participantRequest.setType(ReqType.DENIED);
        ParticipantRequest save = participantRequestRepo.save(participantRequest);
        return MappingUtils.fromParticipantRequestToPartReqDtoResponse(save);
    }


}
