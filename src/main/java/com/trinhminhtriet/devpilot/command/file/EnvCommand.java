package com.trinhminhtriet.devpilot.command.file;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Slf4j
@Component
@Command(
    name = "env",
    description = "Kit for environment configuration files",
    subcommands = {
        EnvDiffCommand.class,
        EnvDiffCommand.class
    },
    mixinStandardHelpOptions = true
)
@RequiredArgsConstructor
public class EnvCommand implements Runnable {

  @Override
  public void run() {
    log.info("Use subcommands: readme, license, editorconfig");
  }
}
