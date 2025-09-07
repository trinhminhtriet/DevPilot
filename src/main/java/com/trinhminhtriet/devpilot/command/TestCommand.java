package com.trinhminhtriet.devpilot.command;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Component
@Slf4j
@Command(name = "test", description = "Run all project tests", mixinStandardHelpOptions = true)
public class TestCommand implements Runnable {

  @Override
  public void run() {
    log.info("Test: Running tests...");
  }
}
