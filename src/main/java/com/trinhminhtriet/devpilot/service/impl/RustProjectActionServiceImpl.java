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
public class RustProjectActionServiceImpl implements ProjectActionService {

  private final TemplateRenderService templateService;

  @Override
  public void scaffoldProject(String projectName, File dir, Map<String, Object> config) throws IOException {
    if (!dir.exists()) {
      dir.mkdirs();
    }
    config.put("projectName", projectName);
    templateService.renderCommonTemplates(config, dir);
    templateService.renderTemplate("rust/gitignore.ftl", config, new File(dir, ".gitignore"));
    templateService.renderTemplate("rust/gitattributes.ftl", config, new File(dir, ".gitattributes"));
    templateService.renderTemplate("rust/editorconfig.ftl", config, new File(dir, ".editorconfig"));
    templateService.renderTemplate("rust/Makefile.ftl", config, new File(dir, "Makefile"));
    templateService.renderTemplate("rust/rustfmt.toml.ftl", config, new File(dir, "rustfmt.toml"));
    templateService.renderTemplate("rust/clippy.toml.ftl", config, new File(dir, "clippy.toml"));
    File srcDir = new File(dir, "src");
    if (!srcDir.exists()) {
      srcDir.mkdirs();
    }
    templateService.renderTemplate("rust/src/main.rs.ftl", config, new File(srcDir, "main.rs"));
  }
}
