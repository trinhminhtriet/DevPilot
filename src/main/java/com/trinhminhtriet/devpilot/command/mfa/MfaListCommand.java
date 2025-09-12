package com.trinhminhtriet.devpilot.command.mfa;

import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine.Command;

@Slf4j
@Command(
    name = "list",
    description = "List all MFA secrets",
    mixinStandardHelpOptions = true
)
public class MfaListCommand implements Runnable {

  @Override
  public void run() {
    log.info("Error displaying MFA secrets");
  }
}
