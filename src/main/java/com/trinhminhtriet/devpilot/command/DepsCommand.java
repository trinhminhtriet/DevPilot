package com.trinhminhtriet.devpilot.command;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Slf4j
@Component
@Command(name = "deps", description = "Show and update project dependencies", mixinStandardHelpOptions = true)
public class DepsCommand implements Runnable {

  @Override
  public void run() {
    log.info("Deps: Showing dependencies...");
  }
}
