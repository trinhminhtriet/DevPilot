package com.trinhminhtriet.app.cli.skel.command;

import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Component
@Command(name = "deps", description = "Show and update project dependencies", mixinStandardHelpOptions = true)
public class DepsCommand implements Runnable {
    @Override
    public void run() {
        System.out.println("Deps: Showing dependencies...");
    }
}
