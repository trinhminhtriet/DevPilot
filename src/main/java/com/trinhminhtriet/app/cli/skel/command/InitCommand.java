package com.trinhminhtriet.app.cli.skel.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Slf4j
@Component
@Command(
    name = "init",
    description = "Initialize project scaffolding",
    subcommands = {
        RustInitCommand.class
    }
)
@RequiredArgsConstructor
public class InitCommand implements Runnable {
  @Override
  public void run() {
    log.info("Vui rồi");
  }
}
