package com.trinhminhtriet.devpilot.service.impl;

import com.trinhminhtriet.devpilot.service.ProjectActionService;
import com.trinhminhtriet.devpilot.service.TemplateRenderService;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class JavaProjectActionServiceImpl implements ProjectActionService {

  private final TemplateRenderService templateService;

  @Override
  public void scaffoldProject(String projectName, File dir, Map<String, Object> config) throws IOException {
    if (!dir.exists()) {
      dir.mkdirs();
    }
    config.put("projectName", projectName);
    templateService.renderCommonTemplates(config, dir);
    templateService.renderTemplate("java/gitignore.ftl", config, new File(dir, ".gitignore"));
    templateService.renderTemplate("java/gitattributes.ftl", config, new File(dir, ".gitattributes"));
    templateService.renderTemplate("java/editorconfig.ftl", config, new File(dir, ".editorconfig"));
    templateService.renderTemplate("java/pom.xml.ftl", config, new File(dir, "pom.xml"));
    File srcDir = new File(dir, "src/main/java");
    if (!srcDir.exists()) {
      srcDir.mkdirs();
    }
    templateService.renderTemplate("java/src/Main.java.ftl", config, new File(srcDir, "Main.java"));
    log.info("Java project '{}' scaffolded at {}", projectName, dir.getAbsolutePath());
  }

  @Override
  public void refactorProject(String projectName, File dir, Map<String, Object> config, String type) throws IOException {
    log.info("[Java] Refactoring project '{}' in '{}' with type '{}'", projectName, dir, type);
    // TODO: Add logic for refactoring Java project
  }
}
