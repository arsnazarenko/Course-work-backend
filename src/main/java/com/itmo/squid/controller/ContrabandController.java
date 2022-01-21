package com.itmo.squid.controller;

import com.itmo.squid.domain.Contraband;
import com.itmo.squid.exception.ResourceNotFoundException;
import com.itmo.squid.repo.ContrabandRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("contraband")
public class ContrabandController {
    private final ContrabandRepo contrabandRepo;

    @Autowired
    public ContrabandController(ContrabandRepo contrabandRepo) {
        this.contrabandRepo = contrabandRepo;
    }

    @GetMapping
    public List<Contraband> list() {
        return contrabandRepo.findAll();
    }
    @GetMapping("{id}")
    public Contraband getOne(@PathVariable(name = "id") Long id) {
        return contrabandRepo.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @PostMapping
    public Contraband create(@RequestBody Contraband contraband) {
        return contrabandRepo.save(contraband);
    }

    @PutMapping("{id}")
    public Contraband update(@PathVariable("id") Long id,
                                            @RequestBody Contraband contraband) {

        Contraband contrabandFromDb = contrabandRepo.findById(id).orElseThrow(ResourceNotFoundException::new);
        BeanUtils.copyProperties(contraband, contrabandFromDb, "id");
        return contrabandRepo.save(contrabandFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        Contraband contraband = contrabandRepo.findById(id).orElseThrow(ResourceNotFoundException::new);
        contrabandRepo.delete(contraband);
    }
}
