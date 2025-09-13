package com.trinhminhtriet.devpilot.command.mfa;

import picocli.CommandLine.Command;

@Command(
    name = "mfa",
    description = "Manage MFA secrets",
    subcommands = {MfaGetCommand.class, MfaListCommand.class},
    mixinStandardHelpOptions = true
)
public class MfaCommand implements Runnable {

  @Override
  public void run() {
    System.out.println("Use subcommands: get, list");
  }
}
