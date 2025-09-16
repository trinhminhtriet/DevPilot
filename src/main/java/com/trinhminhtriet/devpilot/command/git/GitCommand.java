package com.trinhminhtriet.devpilot.command.git;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Slf4j
@Component
@Command(
    name = "git",
    description = "Git utilities",
    subcommands = {
        GitInitCommand.class,
        GitConfigCommand.class
    },
    mixinStandardHelpOptions = true
)
public class GitCommand implements Runnable {

  @Override
  public void run() {
    log.info("Use subcommands: init, config");
  }
}
