package com.trinhminhtriet.app.cli.skel.command;

import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Component
@Command(name = "test", description = "Run all project tests", mixinStandardHelpOptions = true)
public class TestCommand implements Runnable {
    @Override
    public void run() {
        System.out.println("Test: Running tests...");
    }
}
