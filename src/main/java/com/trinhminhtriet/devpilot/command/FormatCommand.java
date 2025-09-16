package com.trinhminhtriet.devpilot.command;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Slf4j
@Component
@Command(name = "format", description = "Format project code", mixinStandardHelpOptions = true)
public class FormatCommand implements Runnable {

  @Override
  public void run() {
    log.info("Format: Formatting code...");
  }
}
