package com.trinhminhtriet.app.cli.skel.command;

import com.trinhminhtriet.app.cli.skel.service.TemplateRenderService;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Log4j2
@Component
@RequiredArgsConstructor
@Command(name = "rust", description = "Generate a Rust project scaffold")
public class RustInitCommand implements Runnable {

  private final TemplateRenderService templateRenderService;

  @Parameters(index = "0", description = "Project name")
  private String projectName;

  @Override
  public void run() {
    Map<String, Object> data = new HashMap<>();
    data.put("projectName", projectName);
    data.put("author", System.getProperty("user.name"));

    String baseDir = "./" + projectName;
    try {
      templateRenderService.renderTemplate("common/README.md.ftl", data, baseDir + "/README.md");
      templateRenderService.renderTemplate("common/LICENSE.ftl", data, baseDir + "/LICENSE");
      templateRenderService.renderTemplate("rust/cargo.toml.ftl", data, baseDir + "/Cargo.toml");
    } catch (IOException e) {
      log.error("Failed to generate project {}", projectName, e);
    }
  }
}
