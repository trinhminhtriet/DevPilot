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
  name = "rust",
  description = "Initialize a new Rust project",
  mixinStandardHelpOptions = true
)
public class InitRustCommand implements Runnable {

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

    createDirectory(dir);

    Map<String, Object> objectMapping = new HashMap<>(configService.loadConfig());
    objectMapping.put("projectName", projectName);

    renderTemplate(() -> templateService.renderCommonTemplates(objectMapping, dir));
    renderTemplate(() -> templateService.renderTemplate("rust/editorconfig.ftl", objectMapping, new File(dir, ".editorconfig")));
    renderTemplate(() -> templateService.renderTemplate("rust/Makefile.ftl", objectMapping, new File(dir, "Makefile")));

    File srcDir = new File(dir, "src");
    createDirectory(srcDir);

    renderTemplate(() -> templateService.renderTemplate("rust/src/main.rs.ftl", objectMapping, new File(srcDir, "main.rs")));

    log.info("Rust project '{}' initialized successfully at {}", projectName, dir.getAbsolutePath());
  }

  private void createDirectory(File directory) {
    if (!directory.exists() && !directory.mkdirs()) {
      throw new RuntimeException();
    }
  }

  private void renderTemplate(RenderAction action) {
    try {
      action.run();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @FunctionalInterface
  private interface RenderAction {
    void run() throws IOException;
  }
}
