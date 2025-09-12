package com.trinhminhtriet.devpilot.command.mfa;

import com.trinhminhtriet.devpilot.service.MfaSecretService;

import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine.Command;

@Slf4j
@Command(
    name = "list",
    description = "List all MFA secrets",
    mixinStandardHelpOptions = true
)
public class MfaListCommand implements Runnable {

  private final MfaSecretService mfaSecretService;

  public MfaListCommand(MfaSecretService mfaSecretService) {
    this.mfaSecretService = mfaSecretService;
  }

  @Override
  public void run() {
    if (mfaSecretService == null) {
      System.out.println("MfaSecretService not injected. Showing placeholder.");
      System.out.println("List all MFA secrets");
      return;
    }
    try {
      String asciiTable = mfaSecretService.toAsciiTable();
      System.out.println(asciiTable);
    } catch (Exception e) {
      log.info("Error displaying MFA secrets", e);
    }
  }
}
