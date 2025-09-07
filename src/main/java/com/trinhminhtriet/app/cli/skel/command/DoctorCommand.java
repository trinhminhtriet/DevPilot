package com.trinhminhtriet.app.cli.skel.command;

import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Component
@Command(name = "doctor", description = "Check environment, versions, system config", mixinStandardHelpOptions = true)
public class DoctorCommand implements Runnable {
    @Override
    public void run() {
        System.out.println("Doctor: Checking environment...");
    }
}
