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
    name = "rust",
    description = "Initialize a new Rust project",
    mixinStandardHelpOptions = true
)
public class RustInitCommand implements Runnable {

  @Option(names = {"--name"}, required = true, description = "Project name")
  private String projectName;

  @Option(names = {"--dir"}, required = true, description = "Target directory")
  private File dir;

  @Option(names = {"--debug"}, description = "Enable debug logging")
  private boolean debug;

  private final TemplateRenderService templateService;

  public RustInitCommand(TemplateRenderService templateService) {
    this.templateService = templateService;
  }

  @Override
  public void run() {
    if (debug) {
      log.debug("Starting Rust project initialization with name={} in dir={}", projectName, dir);
    }

    if (!dir.exists() && !dir.mkdirs()) {
      throw new IllegalStateException("Failed to create directory: " + dir);
    }

    Map<String, Object> objectMapping = new HashMap<>();
    objectMapping.put("projectName", projectName);
    objectMapping.put("authorName", "Trinh Minh Triet");
    objectMapping.put("authorEmail", "contact@trinhminhtriet.com");

    try {
      // README.md
      templateService.renderTemplate("common/README.md.ftl", objectMapping, new File(dir, "README.md"));

      // LICENSE
      templateService.renderTemplate("common/LICENSE.ftl", objectMapping, new File(dir, "LICENSE"));

      // Cargo.toml
      templateService.renderTemplate("rust/cargo.toml.ftl", objectMapping, new File(dir, "Cargo.toml"));

      // src/main.rs
      File srcDir = new File(dir, "src");
      if (!srcDir.exists() && !srcDir.mkdirs()) {
        throw new IllegalStateException("Failed to create src directory: " + srcDir);
      }
      templateService.renderTemplate("rust/src/main.rs.ftl", objectMapping, new File(srcDir, "main.rs"));

      log.info("Rust project '{}' initialized successfully at {}", projectName, dir.getAbsolutePath());
    } catch (Exception e) {
      log.error("Failed to initialize Rust project", e);
      throw new RuntimeException(e);
    }
  }
}
