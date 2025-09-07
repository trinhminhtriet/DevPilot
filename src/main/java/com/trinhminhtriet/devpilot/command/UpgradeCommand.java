package com.trinhminhtriet.devpilot.command;

import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Component
@Command(name = "upgrade", description = "Upgrade DevPilot CLI to latest version", mixinStandardHelpOptions = true)
public class UpgradeCommand implements Runnable {
    @Override
    public void run() {
        System.out.println("Upgrade: Updating DevPilot CLI...");
    }
}
