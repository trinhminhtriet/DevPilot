package com.trinhminhtriet.devpilot.command.file;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(
    name = "switch",
    description = "Switch between .env profiles (dev, staging, prod, ...)",
    mixinStandardHelpOptions = true
)
public class EnvSwitchCommand implements Runnable {

  @Option(names = {"--profile", "-p"}, description = "Profile name (e.g. dev, prod, staging)", required = true)
  String profile;

  @Option(names = {"--dir", "-d"}, description = "Directory containing .env files", defaultValue = ".")
  File dir;

  @Override
  public void run() {
    File src = new File(dir, ".env." + profile);
    File dest = new File(dir, ".env");
    if (!src.exists()) {
      System.err.println("Profile file not found: " + src.getPath());
      return;
    }
    try {
      Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
      System.out.println("Switched to profile: " + profile);
    } catch (Exception e) {
      System.err.println("Failed to switch profile: " + e.getMessage());
    }
  }
}
