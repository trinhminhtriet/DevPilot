package com.trinhminhtriet.app.cli.skel.command;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Slf4j
@Component
@Command(
    name = "skel",
    mixinStandardHelpOptions = true,
    version = "skel 1.0.0",
    description = "Project scaffolding CLI generator",
    subcommands = {
        StrapiCommand.class,
        GitCommand.class,
        AddCommand.class,
        RefactorCommand.class,
        InitCommand.class,
        ListCommand.class,
        FigletCommand.class,
        VersionCommand.class,
        AutoCompletionCommand.class
    }
)
public class RootCommand implements Runnable {

  @Override
  public void run() {
    log.info("Use --help to see available commands.");
  }
}
