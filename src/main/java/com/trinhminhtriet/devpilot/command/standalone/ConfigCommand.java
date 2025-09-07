package com.trinhminhtriet.devpilot.command.standalone;

import com.trinhminhtriet.devpilot.service.ConfigService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@RequiredArgsConstructor
@Component
@Command(
    name = "config",
    description = "Manage user config (author, email, license, etc)",
    mixinStandardHelpOptions = true
)
@Slf4j
public class ConfigCommand implements Runnable {

  private final ConfigService configService;

  @Option(names = {"--edit", "-e"}, description = "Edit config file in default text editor", defaultValue = "false")
  private boolean edit;

  @Override
  public void run() {
    configService.initConfig();
    String configPath = configService.getConfigFilePath();
    log.info("Config file path: {}", configPath);
    if (edit) {
      try {
        String os = System.getProperty("os.name").toLowerCase();
        Process process;
        if (os.contains("win")) {
          process = new ProcessBuilder("notepad", configPath).start();
        } else if (os.contains("mac")) {
          process = new ProcessBuilder("code", "-e", configPath).start();
        } else {
          process = new ProcessBuilder("code", configPath).inheritIO().start();
        }
        process.waitFor();
      } catch (Exception e) {
        log.error("Could not open editor: {}", e.getMessage());
      }
    }
  }
}
