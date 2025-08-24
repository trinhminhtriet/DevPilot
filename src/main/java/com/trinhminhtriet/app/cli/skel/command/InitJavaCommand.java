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
    name = "java",
    description = "Initialize a new Java Maven project",
    mixinStandardHelpOptions = true
)
public class InitJavaCommand implements Runnable {

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
      log.debug("Starting Java Maven project initialization with name={} in dir={}", projectName, dir);
    }
    try {
      if (!dir.exists()) {
        dir.mkdirs();
      }
      Map<String, Object> objectMapping = new HashMap<>(configService.loadConfig());
      objectMapping.put("projectName", projectName);

      templateService.renderCommonTemplates(objectMapping, dir);

      templateService.renderTemplate("java/gitignore.ftl", objectMapping, new File(dir, ".gitignore"));
      templateService.renderTemplate("java/gitattributes.ftl", objectMapping, new File(dir, ".gitattributes"));
      templateService.renderTemplate("java/editorconfig.ftl", objectMapping, new File(dir, ".editorconfig"));
      templateService.renderTemplate("java/pom.xml.ftl", objectMapping, new File(dir, "pom.xml"));
      templateService.renderTemplate("java/Makefile.ftl", objectMapping, new File(dir, "Makefile"));

      File srcDir = new File(dir, "src");
      if (!srcDir.exists()) {
        srcDir.mkdirs();
      }

      log.info("Java Maven project '{}' initialized successfully at {}", projectName, dir.getCanonicalPath());
    } catch (IOException e) {
      log.error("Error initializing Java Maven project", e);
    }
  }
}
