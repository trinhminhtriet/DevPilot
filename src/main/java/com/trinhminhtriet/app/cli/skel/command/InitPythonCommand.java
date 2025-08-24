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
  name = "python",
  description = "Initialize a new Python project",
  mixinStandardHelpOptions = true
)
public class InitPythonCommand implements Runnable {

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
    log.debug("Starting Python project initialization with projectName={} in dir={}", projectName, dir);
  }

  if (!dir.exists()) {
    dir.mkdirs();
  }

  Map<String, Object> objectMapping = new HashMap<>(configService.loadConfig());
  objectMapping.put("projectName", projectName);

  try {
    templateService.renderCommonTemplates(objectMapping, dir);

    File srcDir = new File(dir, "src");
    if (!srcDir.exists()) {
    srcDir.mkdirs();
    }

    templateService.renderTemplate("python/src/main.py.ftl", objectMapping, new File(srcDir, "main.py"));
    templateService.renderTemplate("python/requirements.txt.ftl", objectMapping, new File(dir, "requirements.txt"));
  } catch (IOException e) {
    throw new RuntimeException(e);
  }

  log.info("Python project '{}' initialized successfully at {}", projectName, dir.getAbsolutePath());
  }
}
