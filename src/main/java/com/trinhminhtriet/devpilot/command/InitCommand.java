package com.trinhminhtriet.devpilot.command;

import com.trinhminhtriet.devpilot.command.java.InitJavaCommand;
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
        InitJavaCommand.class
    }
)
@RequiredArgsConstructor
public class InitCommand implements Runnable {

  @Override
  public void run() {
    log.info("Vui rồi");
  }
}
