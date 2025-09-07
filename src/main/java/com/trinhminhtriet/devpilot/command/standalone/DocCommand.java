package com.trinhminhtriet.devpilot.command.standalone;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;


@Component
@Slf4j
@Command(name = "doc", description = "Generate project documentation from code/comments", mixinStandardHelpOptions = true)
public class DocCommand implements Runnable {

  @Override
  public void run() {
    log.info("Doc: Generating documentation...");
  }
}
