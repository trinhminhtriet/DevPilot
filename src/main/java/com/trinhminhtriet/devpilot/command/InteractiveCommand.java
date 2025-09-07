package com.trinhminhtriet.devpilot.command;

import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Component
@Command(name = "interactive", description = "Interactive project initialization", mixinStandardHelpOptions = true)
public class InteractiveCommand implements Runnable {
    @Override
    public void run() {
        System.out.println("Interactive: Starting interactive mode...");
    }
}
