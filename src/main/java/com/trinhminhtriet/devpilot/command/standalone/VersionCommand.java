package com.trinhminhtriet.devpilot.command.standalone;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Slf4j
@Component
@Command(name = "version", description = "Show CLI version")
public class VersionCommand implements Runnable {

  @Override
  public void run() {
    log.info("skel CLI version 1.0.0");
  }
}
