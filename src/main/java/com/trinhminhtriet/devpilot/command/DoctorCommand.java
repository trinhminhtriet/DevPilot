package com.trinhminhtriet.devpilot.command;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Component
@Slf4j
@Command(name = "doctor", description = "Check environment, versions, system config", mixinStandardHelpOptions = true)
public class DoctorCommand implements Runnable {

  @Override
  public void run() {
    log.info("Doctor: Checking environment...");
  }
}
