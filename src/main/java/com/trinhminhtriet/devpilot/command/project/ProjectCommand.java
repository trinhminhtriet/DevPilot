package com.trinhminhtriet.devpilot.command.project;

import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Component
@Command(
    name = "project",
    description = "Project management commands",
    mixinStandardHelpOptions = true,
    subcommands = {
        ProjectInitCommand.class
        // Add more subcommands here: ProjectInfoCommand.class, ProjectCleanCommand.class, ...
    }
)
public class ProjectCommand implements Runnable {
    @Override
    public void run() {
        System.out.println("Use subcommands: init, info, clean, release, ...");
    }
}
