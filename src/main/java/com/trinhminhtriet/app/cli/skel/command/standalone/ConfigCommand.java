package com.trinhminhtriet.app.cli.skel.command.standalone;

import com.trinhminhtriet.app.cli.skel.service.ConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@RequiredArgsConstructor
@Component
@Command(name = "config", description = "Manage user config (author, email, license, etc)", mixinStandardHelpOptions = true)
public class ConfigCommand implements Runnable {

  @Option(names = {"--set"}, description = "Set config key=value, e.g. --set author.name=YourName", split = ",")
  private String[] setValues;

  @Option(names = {"--show"}, description = "Show current config", defaultValue = "false")
  private boolean show;

  private final ConfigService configService;

  @Override
  public void run() {

    if (show || (setValues == null || setValues.length == 0)) {
      System.out.println("Current config:");
    }
  }
}
