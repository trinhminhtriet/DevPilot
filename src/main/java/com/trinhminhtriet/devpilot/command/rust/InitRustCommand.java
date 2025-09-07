package com.trinhminhtriet.devpilot.command.rust;

import com.trinhminhtriet.devpilot.service.ConfigService;
import com.trinhminhtriet.devpilot.service.TemplateRenderService;
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
    name = "rust",
    description = "Initialize a new Rust project",
    mixinStandardHelpOptions = true
)
public class InitRustCommand extends AbstractRustCommand implements Runnable {

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
      log.debug("Starting Rust project initialization with name={} in dir={}", projectName, dir);
    }
    try {
      if (!dir.exists()) {
        dir.mkdirs();
      }

      Map<String, Object> objectMapping = new HashMap<>(configService.loadConfig());
      objectMapping.put("projectName", projectName);

      renderTemplate(templateService, objectMapping, dir);

      File srcDir = new File(dir, "src");
      if (!srcDir.exists()) {
        srcDir.mkdirs();
      }

      templateService.renderTemplate("rust/src/main.rs.ftl", objectMapping, new File(srcDir, "main.rs"));

      log.info("Rust project '{}' initialized successfully at {}", projectName, dir.getCanonicalPath());
    } catch (IOException e) {
      log.error("Error initializing Rust project", e);
    }
  }
}
