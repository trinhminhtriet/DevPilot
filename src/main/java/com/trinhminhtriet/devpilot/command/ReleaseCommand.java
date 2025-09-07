package com.trinhminhtriet.devpilot.command;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Component
@Slf4j
@Command(name = "release", description = "Create new release (tag, changelog, build, publish)", mixinStandardHelpOptions = true)
public class ReleaseCommand implements Runnable {

  @Override
  public void run() {
    log.info("Release: Creating new release...");
  }
}
