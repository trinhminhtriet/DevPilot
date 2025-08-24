package com.trinhminhtriet.app.cli.skel.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;


@Slf4j
@Component
@Command(
    name = "init",
    description = "Initialize project scaffolding",
    subcommands = {
        InitGoCommand.class,
        InitRustCommand.class,
        InitPythonCommand.class,
    }
)
@RequiredArgsConstructor
public class InitCommand implements Runnable {

  @Override
  public void run() {
    log.info("Vui rồi");
  }
}
