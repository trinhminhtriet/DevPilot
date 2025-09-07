package com.trinhminhtriet.devpilot.command;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Component
@Slf4j
@Command(name = "serve", description = "Run dev server (web, API, docs, etc)", mixinStandardHelpOptions = true)
public class ServeCommand implements Runnable {

  @Override
  public void run() {
    log.info("Serve: Running dev server...");
  }
}
