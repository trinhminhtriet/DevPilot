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
public class TypescriptProjectActionServiceImpl implements ProjectActionService {

  private final TemplateRenderService templateService;

  @Override
  public void scaffoldProject(String projectName, File dir, Map<String, Object> config) throws IOException {
    if (!dir.exists()) {
      dir.mkdirs();
    }
    config.put("projectName", projectName);
    templateService.renderCommonTemplates(config, dir);
    templateService.renderTemplate("typescript/gitignore.ftl", config, new File(dir, ".gitignore"));
    templateService.renderTemplate("typescript/gitattributes.ftl", config, new File(dir, ".gitattributes"));
    templateService.renderTemplate("typescript/editorconfig.ftl", config, new File(dir, ".editorconfig"));
    templateService.renderTemplate("typescript/package.json.ftl", config, new File(dir, "package.json"));
    templateService.renderTemplate("typescript/tsconfig.json.ftl", config, new File(dir, "tsconfig.json"));
    File srcDir = new File(dir, "src");
    if (!srcDir.exists()) {
      srcDir.mkdirs();
    }
    templateService.renderTemplate("typescript/src/index.ts.ftl", config, new File(srcDir, "index.ts"));
  }

  @Override
  public void refactorProject(String projectName, File dir, Map<String, Object> config, String type) throws IOException {
    log.info("[TypeScript] Refactoring project '{}' in '{}' with type '{}'", projectName, dir, type);
    // TODO: Add logic for refactoring TypeScript project
  }
}
