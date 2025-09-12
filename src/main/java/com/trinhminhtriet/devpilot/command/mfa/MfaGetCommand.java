package com.trinhminhtriet.devpilot.command.mfa;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "get", description = "Get MFA secret by id", mixinStandardHelpOptions = true)
public class MfaGetCommand implements Runnable {

  @Option(names = {"--id", "-i"}, required = true, description = "MFA secret id")
  Long id;

  @Override
  public void run() {
    System.out.println("get");
  }
}
