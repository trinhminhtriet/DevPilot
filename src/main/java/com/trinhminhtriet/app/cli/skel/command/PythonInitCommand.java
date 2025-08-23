package com.trinhminhtriet.app.cli.skel.command;

import com.trinhminhtriet.app.cli.skel.service.TemplateRenderService;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Slf4j
@Component
@Command(
    name = "python",
    description = "Initialize a new Python project",
    mixinStandardHelpOptions = true
)
public class PythonInitCommand implements Runnable {

  @Option(names = {"--name"}, required = true, description = "Project name")
  private String projectName;

  @Option(names = {"--dir"}, required = true, description = "Target directory")
  private File dir;

  @Option(names = {"--debug"}, description = "Enable debug logging")
  private boolean debug;

  private final TemplateRenderService templateService;

  public PythonInitCommand(TemplateRenderService templateService) {
    this.templateService = templateService;
  }

  @Override
  public void run() {
    if (debug) {
      log.debug("Starting Python project initialization with projectName={} in dir={}", projectName, dir);
    }

    if (!dir.exists() && !dir.mkdirs()) {
      throw new IllegalStateException("Failed to create directory: " + dir);
    }

    Map<String, Object> objectMapping = new HashMap<>();
    objectMapping.put("projectName", projectName);
    objectMapping.put("authorName", "Trinh Minh Triet");
    objectMapping.put("authorEmail", "contact@trinhminhtriet.com");

    try {
      templateService.renderCommonTemplates(objectMapping, dir);

      // Create src directory
      File srcDir = new File(dir, "src");
      if (!srcDir.exists() && !srcDir.mkdirs()) {
        throw new IllegalStateException("Failed to create src directory: " + srcDir);
      }

      // main.py
      templateService.renderTemplate("python/src/main.py.ftl", objectMapping, new File(srcDir, "main.py"));

      // requirements.txt
      templateService.renderTemplate("python/requirements.txt.ftl", objectMapping, new File(dir, "requirements.txt"));

      log.info("Python project '{}' initialized successfully at {}", projectName, dir.getAbsolutePath());
    } catch (Exception e) {
      log.error("Failed to initialize Python project", e);
      throw new RuntimeException(e);
    }
  }
}
