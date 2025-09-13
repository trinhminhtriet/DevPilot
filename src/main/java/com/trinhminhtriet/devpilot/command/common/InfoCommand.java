package com.trinhminhtriet.devpilot.command.common;

import com.trinhminhtriet.devpilot.service.ConfigService;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Slf4j
@RequiredArgsConstructor
@Command(
    name = "info",
    description = "Show project information (name, version, author, license, etc)",
    mixinStandardHelpOptions = true
)
public class InfoCommand implements Runnable {

  @Option(names = {"--dir"}, description = "Project directory", required = false)
  private File dir = new File(".");

  private final ConfigService configService;

  @Override
  public void run() {
    Map<String, Object> config = new HashMap<>(configService.loadConfig());
    String projectName = config.getOrDefault("projectName", dir.getName()).toString();
    String author = "";

    String license = config.getOrDefault("license", "").toString();
    String version = config.getOrDefault("version", "0.1.0").toString();
    log.info("Project: " + projectName);
    log.info("Version: " + version);
    log.info("Author: " + author);
    log.info("License: " + license);
    log.info("Directory: " + dir.getAbsolutePath());
  }
}
