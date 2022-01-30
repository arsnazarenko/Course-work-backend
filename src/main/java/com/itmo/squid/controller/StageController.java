package com.itmo.squid.controller;
import com.itmo.squid.domain.*;
import com.itmo.squid.dto.MappingUtils;
import com.itmo.squid.dto.StageReqDto;
import com.itmo.squid.dto.StageRespDto;
import com.itmo.squid.dto.TeamsDto;
import com.itmo.squid.exception.BadRequestException;
import com.itmo.squid.exception.ResourceNotFoundException;
import com.itmo.squid.repo.AttributeRepo;
import com.itmo.squid.repo.ParticipantRepo;
import com.itmo.squid.repo.StageRepo;
import com.itmo.squid.repo.TeamOnStageRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin()
@RestController
@RequestMapping("stage")
public class StageController {

    private final StageRepo stageRepo;
    private final AttributeRepo attributeRepo;
    private final ParticipantRepo participantRepo;
    private final TeamOnStageRepo teamOnStageRepo;

    public StageController(StageRepo stageRepo, AttributeRepo attributeRepo, ParticipantRepo participantRepo, TeamOnStageRepo teamOnStageRepo) {
        this.stageRepo = stageRepo;
        this.attributeRepo = attributeRepo;
        this.participantRepo = participantRepo;
        this.teamOnStageRepo = teamOnStageRepo;
    }


    @GetMapping
    public List<StageRespDto> list() {
        return stageRepo.findAll().stream().map(MappingUtils::fromStageEntityToDto).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public StageRespDto getOne(@PathVariable(name = "id") Long id) {
        return stageRepo.findById(id).map(MappingUtils::fromStageEntityToDto).orElseThrow(ResourceNotFoundException::new);
    }

    @PostMapping
    public StageRespDto create(@RequestBody StageReqDto stage) {
        Stage newStage = MappingUtils.fromStageDtoToEntity(stage);
        Set<Attribute> newAttributes = stage.getAttributesIds()
                .stream()
                .map(attributeRepo::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
        newStage.setAttributes(newAttributes);
        return MappingUtils.fromStageEntityToDto(stageRepo.save(newStage));
    }

    @PutMapping("{id}/start")
    public ResponseEntity<StageRespDto> startStage(@PathVariable(name = "id") Long id, @RequestBody TeamsDto teams ) {
        Stage stage = stageRepo.findById(id).orElseThrow(ResourceNotFoundException::new);
        Optional<Stage> alreadyConStage = stageRepo.findStageByStatusEquals(StageStatus.CONTINUOUS);
        if (alreadyConStage.isPresent()) {
            return ResponseEntity.badRequest().body(MappingUtils.fromStageEntityToDto(alreadyConStage.get()));
        }
        if (stage.getStatus() != StageStatus.END) {
            if (stage.getType() != TeamType.SINGLE) {
                createStageTeams(teams, stage);
            }
            stage.setStatus(StageStatus.CONTINUOUS);
            return ResponseEntity.ok(MappingUtils.fromStageEntityToDto(stageRepo.save(stage)));
        } else {
            return ResponseEntity.badRequest().body(MappingUtils.fromStageEntityToDto(stage));
        }
    }

    private void createStageTeams(TeamsDto teams, Stage stage) throws BadRequestException {
        Set<TeamOnStage> stageTeams = stage.getTeams();
        for (Set<Long> team: teams.getTeams()) {
            Set<Participant> curTeam = team.stream().map(participantRepo::findById)
                    .map(v -> {
                        if (v.isPresent()) {
                            return v.get();
                        } else {
                            throw new BadRequestException();
                        }
                    })
                    .peek(p ->{
                        if (!p.isAlive()) {
                            throw new BadRequestException();
                        }
                    })
                    .collect(Collectors.toSet());
            TeamOnStage savedTeam = teamOnStageRepo.save(new TeamOnStage(null, stage, stage.getType(), curTeam));
            stageTeams.add(savedTeam);
        }
    }

    @PutMapping("end")
    public StageRespDto endStage() {
        Stage conStage = stageRepo.findStageByStatusEquals(StageStatus.CONTINUOUS)
                .orElseThrow(BadRequestException::new);
        conStage.setStatus(StageStatus.END);
        return MappingUtils.fromStageEntityToDto(stageRepo.save(conStage));
    }

}
