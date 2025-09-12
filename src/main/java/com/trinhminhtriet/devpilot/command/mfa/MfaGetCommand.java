package com.trinhminhtriet.devpilot.command.mfa;

import com.trinhminhtriet.devpilot.dto.MfaSecretDto;
import com.trinhminhtriet.devpilot.service.MfaSecretService;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "get", description = "Get MFA secret by id", mixinStandardHelpOptions = true)
public class MfaGetCommand implements Runnable {

  @Option(names = {"--id", "-i"}, required = true, description = "MFA secret id")
  Long id;

  private final MfaSecretService mfaSecretService;

  public MfaGetCommand(MfaSecretService mfaSecretService) {
    this.mfaSecretService = mfaSecretService;
  }

  @Override
  public void run() {
    MfaSecretDto mfaSecretDto = mfaSecretService.get(id);
  }
}
