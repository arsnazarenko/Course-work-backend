package com.itmo.squid.controller;

import com.itmo.squid.domain.Attribute;
import com.itmo.squid.domain.Stage;
import com.itmo.squid.exception.BadRequestException;
import com.itmo.squid.exception.ResourceNotFoundException;
import com.itmo.squid.repo.AttributeRepo;
import com.itmo.squid.repo.StageRepo;
import com.itmo.squid.repo.StageStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("stage")
public class StageController {

    private final StageRepo stageRepo;
    private final StageStatusRepo stageStatusRepo;
    private final AttributeRepo attributeRepo;

    @Autowired
    public StageController(StageRepo stageRepo, StageStatusRepo stageStatusRepo, AttributeRepo attributeRepo) {
        this.stageRepo = stageRepo;
        this.stageStatusRepo = stageStatusRepo;
        this.attributeRepo = attributeRepo;
    }

    @GetMapping
    public List<Stage> list() {
        return stageRepo.findAll();
    }

    @GetMapping("{id}")
    public Stage getOne(@PathVariable(name = "id") Long id) {
        return stageRepo.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @PostMapping
    public Stage create(@RequestBody Stage stage) {
        stage.setStatus(stageStatusRepo.getById(Status.NOT_OPEN.id));
        stage.setAmountOfDeath(0);
        stage.setAttributes(new HashSet<>());
        return stageRepo.save(stage);
    }

    @PostMapping("{id}/attributes")
    public Set<Attribute> addAttribute(@PathVariable("id") Long id, @RequestBody Map<String, Long> attribute) {
        Attribute attr = Optional.ofNullable(attribute.get("id"))
                .flatMap(attributeRepo::findById)
                .orElseThrow(BadRequestException::new);

        return stageRepo.findById(id)
                .map(v -> {
                    v.getAttributes().add(attr);
                    stageRepo.save(v);
                    return v.getAttributes();
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @DeleteMapping("{id}/attributes")
    public boolean removeAttribute(@PathVariable("id") Long id, @RequestBody Map<String, Long> attribute) {
        Attribute attr = Optional.ofNullable(attribute.get("id"))
                .flatMap(attributeRepo::findById)
                .orElseThrow(BadRequestException::new);

        return stageRepo.findById(id)
                .map(v -> {
                    boolean result = v.getAttributes().remove(attr);
                    stageRepo.save(v);
                    return result;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @GetMapping("{id}/attributes")
    public Set<Attribute> getAttributes(@PathVariable("id") Long id) {
        return stageRepo.findById(id)
                .map(Stage::getAttributes)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @PutMapping("{id}/start")
    public ResponseEntity<Stage> startStage(@PathVariable(name = "id") Long id) {
        Stage stage = stageRepo.findById(id).orElseThrow(ResourceNotFoundException::new);
        ResponseEntity<Stage> conStage = stageRepo.findAll().stream()
                .filter(s -> s.getStatus().getId().equals(Status.CONTINUOUS.id))
                .findFirst().map(s -> ResponseEntity.badRequest().body(s)).orElse(ResponseEntity.ok(null));
        if (conStage.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
            return conStage;
        }

        if (!stage.getStatus().getId().equals(Status.END.id)) {
            stage.setStatus(stageStatusRepo.getById(Status.CONTINUOUS.id));
            return ResponseEntity.ok(stageRepo.save(stage));
        } else {
            return ResponseEntity.badRequest().body(stageRepo.save(stage));
        }

    }

    @PutMapping("end")
    public Stage endStage() {
        return stageRepo.findAll().stream()
                .filter(s -> s.getStatus().getId().equals(Status.CONTINUOUS.id))
                .findFirst().map(s -> {
                    s.setStatus(stageStatusRepo.getById(Status.END.id));
                    return stageRepo.save(s);
                })
                .orElseThrow(BadRequestException::new);
    }


    enum Status {
        CONTINUOUS("continuous", 1L), END("end", 2L), NOT_OPEN("not_open", 3L);

        final String statusAsStr;
        final Long id;

        Status(String statusAsStr, Long id) {
            this.statusAsStr = statusAsStr;
            this.id = id;
        }
    }

}
