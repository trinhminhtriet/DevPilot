package com.trinhminhtriet.devpilot.command;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Component
@Slf4j
@Command(name = "upgrade", description = "Upgrade DevPilot CLI to latest version", mixinStandardHelpOptions = true)
public class UpgradeCommand implements Runnable {

  @Override
  public void run() {
    log.info("Upgrade: Updating DevPilot CLI...");
  }
}
