package com.testmanager.tmutility.controller;

import com.testmanager.tmutility.services.CommandServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/file/")
public class FileController {

    private final CommandServices commandServices;

    @Autowired
    public FileController(CommandServices commandServices){
        this.commandServices = commandServices;
    }

    @PostMapping("/json")
    public ResponseEntity executeCommand(@RequestParam(name="command") String command) {
        return commandServices.executeShellCommand(command);
    }

    @PostMapping("/copy")
    public ResponseEntity killProcess(@RequestParam(name="name") String name) {
        return commandServices.killProcess(name);
    }

    @GetMapping("/json")
    public ResponseEntity getProcess(@RequestParam(name="name") String name) {
        return getProcess(name);
    }
}
