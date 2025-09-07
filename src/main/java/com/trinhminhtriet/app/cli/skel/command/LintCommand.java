package com.trinhminhtriet.app.cli.skel.command;

import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Component
@Command(name = "lint", description = "Run linter for project", mixinStandardHelpOptions = true)
public class LintCommand implements Runnable {
    @Override
    public void run() {
        System.out.println("Lint: Running linter...");
    }
}
