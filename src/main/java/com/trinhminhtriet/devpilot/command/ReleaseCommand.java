package com.trinhminhtriet.devpilot.command;

import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Component
@Command(name = "release", description = "Create new release (tag, changelog, build, publish)", mixinStandardHelpOptions = true)
public class ReleaseCommand implements Runnable {
    @Override
    public void run() {
        System.out.println("Release: Creating new release...");
    }
}
