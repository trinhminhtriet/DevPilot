package com.trinhminhtriet.app.cli.skel.command;

import com.trinhminhtriet.app.cli.skel.service.ProjectGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Component
@Command(name = "init", description = "Initialize a new project in Go, Rust, Python, etc.")
@RequiredArgsConstructor
public class InitCommand implements Runnable {

  private ProjectGeneratorService projectGeneratorService;

  @Parameters(index = "0", description = "Language (go, rust, python, ts)")
  private String language;

  @Parameters(index = "1", description = "Project name")
  private String projectName;

  @Override
  public void run() {
    projectGeneratorService.generate(language, projectName);
  }
}
