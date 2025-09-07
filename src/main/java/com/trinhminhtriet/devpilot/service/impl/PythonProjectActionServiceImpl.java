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
public class PythonProjectActionServiceImpl implements ProjectActionService {

  private final TemplateRenderService templateService;

  @Override
  public void scaffoldProject(String projectName, File dir, Map<String, Object> config) throws IOException {
    if (!dir.exists()) {
      dir.mkdirs();
    }
    config.put("projectName", projectName);
    templateService.renderCommonTemplates(config, dir);
    templateService.renderTemplate("python/gitignore.ftl", config, new File(dir, ".gitignore"));
    templateService.renderTemplate("python/gitattributes.ftl", config, new File(dir, ".gitattributes"));
    templateService.renderTemplate("python/editorconfig.ftl", config, new File(dir, ".editorconfig"));
    templateService.renderTemplate("python/requirements.txt.ftl", config, new File(dir, "requirements.txt"));
    File srcDir = new File(dir, "src");
    if (!srcDir.exists()) {
      srcDir.mkdirs();
    }
    templateService.renderTemplate("python/src/main.py.ftl", config, new File(srcDir, "main.py"));
  }
}
