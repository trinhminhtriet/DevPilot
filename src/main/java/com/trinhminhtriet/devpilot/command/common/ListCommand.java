package com.trinhminhtriet.devpilot.command.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Slf4j
@Component
@Command(name = "list", description = "List available project templates")
public class ListCommand implements Runnable {

  @Override
  public void run() {
    log.info("Available templates: go, rust, python, ts");
  }
}
