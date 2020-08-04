package com.testmanager.tmutility.services;

import org.apache.commons.lang3.SystemUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class FileServices {

    public ResponseEntity executeShellCommand(String command){

        return getRunCommand(command);
    }

    private ResponseEntity updateJson(String filepath, ) {
        ProcessBuilder processBuilder = new ProcessBuilder();
        Process process;

        if (SystemUtils.IS_OS_LINUX) {
            processBuilder.command("bash", "-c", command);
        }
        else {
            processBuilder.command("powershell.exe", "/c", command);
        }
        try {
            process = processBuilder.start();
            StringBuilder output = new StringBuilder();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

            int exitVal = process.waitFor();
            if (exitVal == 0) {
                return new ResponseEntity(output, HttpStatus.OK);
            } else {
                return new ResponseEntity("failed to execute command", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        catch (IOException e){
            return new ResponseEntity("IO exception occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (InterruptedException e) {
            return new ResponseEntity("error in reading stream for output", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity killProcess(String name){
        String command;

        if (SystemUtils.IS_OS_LINUX) {
            command = "pkill " + name;
        }
        else {
            command = "taskkill /F /im " + name;
        }
        return getRunCommand(command);

    }

    public ResponseEntity getProcess(String name){
        String command;
        if (SystemUtils.IS_OS_LINUX) {
            command = "pidof " + name;
        }
        else {
            command = "Get-Process -name " + name;
        }
        return getRunCommand(command);
    }
}
