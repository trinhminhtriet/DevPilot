package com.trinhminhtriet.app.cli.skel.command;

import com.trinhminhtriet.app.cli.skel.service.ProjectGeneratorService;
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

  private ProjectGeneratorService projectGeneratorService;

  @Parameters(index = "0", description = "Language (go, rust, python, ts)")
  private String language;

  @Parameters(index = "1", description = "Project name")
  private String projectName;

  @Override
  public void run() {
    log.info("Generating {} project: {}", language, projectName);
  }
}
