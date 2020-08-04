package com.testmanager.tmutility.controller;

import com.testmanager.tmutility.services.CommandServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommandController {

    private final CommandServices commandServices;

    @Autowired
    public CommandController(CommandServices commandServices){
        this.commandServices = commandServices;
    }

    @PostMapping("/command")
    public ResponseEntity executeCommand(@RequestParam(name="command") String command) {
        return commandServices.executeShellCommand(command);
    }

    @PostMapping("/process/kill")
    public ResponseEntity killProcess(@RequestParam(name="name") String name) {
        return commandServices.killProcess(name);
    }

    @GetMapping("/process")
    public ResponseEntity getProcess(@RequestParam(name="name") String name) {
        return getProcess(name);
    }
}
