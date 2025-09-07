package com.trinhminhtriet.devpilot.command;

import com.trinhminhtriet.devpilot.command.go.InitGoCommand;
import com.trinhminhtriet.devpilot.command.java.InitJavaCommand;
import com.trinhminhtriet.devpilot.command.python.InitPythonCommand;
import com.trinhminhtriet.devpilot.command.rust.InitRustCommand;
import com.trinhminhtriet.devpilot.command.typescript.InitTypescriptCommand;
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
        InitTypescriptCommand.class,
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
