package com.ets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ets.model.CodingProgram;
import com.ets.service.CodingProgramService;

import java.util.List;

@RestController
@RequestMapping("/api/coding")
@CrossOrigin
public class CodingProgramController {

    @Autowired
    private CodingProgramService service;

    @PostMapping
    public CodingProgram create(@RequestBody CodingProgram coding) {
        return service.create(coding);
    }

    @GetMapping
    public List<CodingProgram> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public CodingProgram getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public CodingProgram update(@PathVariable Long id,
                                @RequestBody CodingProgram coding) {
        return service.update(id, coding);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
