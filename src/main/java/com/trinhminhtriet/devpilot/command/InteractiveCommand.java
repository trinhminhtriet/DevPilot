package com.trinhminhtriet.devpilot.command;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Slf4j
@Component
@Command(name = "interactive", description = "Interactive project initialization", mixinStandardHelpOptions = true)
public class InteractiveCommand implements Runnable {
    @Override
    public void run() {
        log.info("Interactive: Starting interactive mode...");
    }
}
