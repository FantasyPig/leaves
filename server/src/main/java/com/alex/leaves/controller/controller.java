package com.alex.leaves.controller;

import com.alex.leaves.service.IdGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/leaves")
public class controller {

    @Autowired
    IdGeneratorService service;

    @GetMapping("/next")
    public Long nextId(@RequestParam("name") String name) {
        return service.nextId(name);
    }
}
