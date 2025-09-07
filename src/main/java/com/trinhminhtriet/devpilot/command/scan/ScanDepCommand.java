package com.trinhminhtriet.devpilot.command.scan;

import java.io.File;
import picocli.CommandLine;

@CommandLine.Command(
    name = "dep",
    description = "Scan project dependencies for outdated and vulnerable packages",
    mixinStandardHelpOptions = true
)
public class ScanDepCommand implements Runnable {

  @CommandLine.Option(names = "--outdated", description = "Show outdated dependencies")
  boolean outdated;

  @CommandLine.Option(names = "--vulnerable", description = "Show vulnerable dependencies")
  boolean vulnerable;

  @CommandLine.Option(names = {"--path", "-p"}, description = "Project path", defaultValue = ".")
  File path;

  @Override
  public void run() {
    if (!outdated && !vulnerable) {
      System.out.println("Please specify --outdated and/or --vulnerable");
      return;
    }
    if (outdated) {
      scanOutdated();
    }
    if (vulnerable) {
      scanVulnerable();
    }
  }

  private void scanOutdated() {
    System.out.println("[Outdated] Scanning for outdated dependencies...");
    // Maven
    File pom = new File(path, "pom.xml");
    if (pom.exists()) {
      System.out.println("Detected Maven project. You can run:");
      System.out.println("  mvn versions:display-dependency-updates");
    }
    // npm
    File pkg = new File(path, "package.json");
    if (pkg.exists()) {
      System.out.println("Detected npm project. You can run:");
      System.out.println("  npm outdated");
    }
    // Python
    File req = new File(path, "requirements.txt");
    if (req.exists()) {
      System.out.println("Detected Python project. You can run:");
      System.out.println("  pip list --outdated");
    }
  }

  private void scanVulnerable() {
    System.out.println("[Vulnerable] Scanning for vulnerable dependencies...");
    // Maven
    File pom = new File(path, "pom.xml");
    if (pom.exists()) {
      System.out.println("Detected Maven project. You can use:");
      System.out.println("  mvn org.owasp:dependency-check-maven:check");
    }
    // npm
    File pkg = new File(path, "package.json");
    if (pkg.exists()) {
      System.out.println("Detected npm project. You can use:");
      System.out.println("  npm audit");
    }
    // Python
    File req = new File(path, "requirements.txt");
    if (req.exists()) {
      System.out.println("Detected Python project. You can use:");
      System.out.println("  pip-audit -r requirements.txt");
    }
  }
}