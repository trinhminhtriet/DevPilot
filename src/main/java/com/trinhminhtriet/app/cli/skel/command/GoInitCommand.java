package com.trinhminhtriet.app.cli.skel.command;

import com.trinhminhtriet.app.cli.skel.service.ConfigService;
import com.trinhminhtriet.app.cli.skel.service.TemplateRenderService;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Slf4j
@Component
@RequiredArgsConstructor
@Command(
    name = "go",
    description = "Initialize a new Go project",
    mixinStandardHelpOptions = true
)
public class GoInitCommand implements Runnable {

  @Option(names = {"--name"}, required = true, description = "Project name")
  private String projectName;

  @Option(names = {"--dir"}, required = true, description = "Target directory")
  private File dir;

  @Option(names = {"--debug"}, description = "Enable debug logging")
  private boolean debug;

  private final TemplateRenderService templateService;
  private final ConfigService configService;

  @Override
  public void run() {
    if (debug) {
      log.debug("Starting Go project initialization with name={} in dir={}", projectName, dir);
    }

    if (!dir.exists() && !dir.mkdirs()) {
      throw new IllegalStateException("Failed to create directory: " + dir);
    }

    Map<String, Object> objectMapping = new HashMap<>(configService.loadConfig());

    try {
      templateService.renderCommonTemplates(objectMapping, dir);
    } catch (IOException e) {
      log.error("❌ Error generating common templates {}", dir, e);
      throw new RuntimeException(e);
    }

    try {
      // .editorconfig
      templateService.renderTemplate("golang/editorconfig.ftl", objectMapping, new File(dir, ".editorconfig"));

      // go.mod
      templateService.renderTemplate("golang/go.mod.ftl", objectMapping, new File(dir, "go.mod"));

      // .gitignore
      templateService.renderTemplate("golang/gitignore.ftl", objectMapping, new File(dir, ".gitignore"));

      // Create src directory
      File srcDir = new File(dir, "src");
      if (!srcDir.exists() && !srcDir.mkdirs()) {
        throw new IllegalStateException("Failed to create src directory.");
      }

      // main.go
      templateService.renderTemplate("go/src/main.go.ftl", objectMapping, new File(srcDir, "main.go"));

      log.info("Go project '{}' initialized successfully at {}", projectName, dir.getAbsolutePath());
    } catch (Exception e) {
      log.error("Failed to initialize Go project", e);
      throw new RuntimeException(e);
    }

    log.info("Go project '{}' initialized successfully at {}", projectName, dir.getAbsolutePath());

  }
}
