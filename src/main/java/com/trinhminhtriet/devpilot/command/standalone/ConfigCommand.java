package com.trinhminhtriet.devpilot.command.standalone;

import com.trinhminhtriet.devpilot.service.ConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@RequiredArgsConstructor
@Component
@Command(
    name = "config",
    description = "Manage user config (author, email, license, etc)",
    mixinStandardHelpOptions = true
)
public class ConfigCommand implements Runnable {

  private final ConfigService configService;

  @Override
  public void run() {

    String configPath = configService.getConfigFilePath();
    System.out.println("Config file path: " + configPath);
  }
}
