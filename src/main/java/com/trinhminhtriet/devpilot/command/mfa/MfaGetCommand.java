package com.trinhminhtriet.devpilot.command.mfa;

import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Slf4j
@Command(name = "get", description = "Get MFA secret by id", mixinStandardHelpOptions = true)
public class MfaGetCommand implements Runnable {

  @Option(names = {"--id", "-i"}, required = true, description = "MFA secret id")
  Long id;

  @Override
  public void run() {
    log.info("MFA secret id: " + id);
  }
}
