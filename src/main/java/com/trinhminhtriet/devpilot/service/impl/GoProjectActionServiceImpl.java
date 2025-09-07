package com.trinhminhtriet.devpilot.service.impl;

import com.trinhminhtriet.devpilot.service.ProjectActionService;
import com.trinhminhtriet.devpilot.service.TemplateRenderService;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GoProjectActionServiceImpl implements ProjectActionService {

  private final TemplateRenderService templateService;

  @Override
  public void scaffoldProject(String projectName, File dir, Map<String, Object> config) throws IOException {
    if (!dir.exists()) {
      dir.mkdirs();
    }
    config.put("projectName", projectName);
    templateService.renderCommonTemplates(config, dir);
    templateService.renderTemplate("go/gitignore.ftl", config, new File(dir, ".gitignore"));
    templateService.renderTemplate("go/gitattributes.ftl", config, new File(dir, ".gitattributes"));
    templateService.renderTemplate("go/editorconfig.ftl", config, new File(dir, ".editorconfig"));
    templateService.renderTemplate("go/go.mod.ftl", config, new File(dir, "go.mod"));
    File srcDir = new File(dir, "src");
    if (!srcDir.exists()) {
      srcDir.mkdirs();
    }
    templateService.renderTemplate("go/src/main.go.ftl", config, new File(srcDir, "main.go"));
  }
}
