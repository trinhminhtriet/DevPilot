package com.trinhminhtriet.devpilot.command.project;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ProjectCommand implements Runnable {

  @Override
  public void run() {
    log.info("Use subcommands: init, info, clean, release, ...");
  }
}
