package com.trinhminhtriet.devpilot.command;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Slf4j
@Component
@Command(name = "lint", description = "Run linter for project", mixinStandardHelpOptions = true)
public class LintCommand implements Runnable {

  @Override
  public void run() {
    log.info("Lint: Running linter...");
  }
}
