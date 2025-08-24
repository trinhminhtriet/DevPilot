package com.trinhminhtriet.app.cli.skel.service.impl;

import com.trinhminhtriet.app.cli.skel.service.TemplateRenderService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class TemplateRenderServiceImpl implements TemplateRenderService {

  private final Configuration freemarkerConfig;

  public TemplateRenderServiceImpl() {
    freemarkerConfig = new Configuration(Configuration.VERSION_2_3_31);
    freemarkerConfig.setClassLoaderForTemplateLoading(getClass().getClassLoader(), "templates");
    freemarkerConfig.setDefaultEncoding("UTF-8");
    freemarkerConfig.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    freemarkerConfig.setLogTemplateExceptions(false);
    freemarkerConfig.setWrapUncheckedExceptions(true);
  }

  @Override
  public void renderTemplate(String templatePath, Map<String, Object> data, File outputFile) throws IOException {
    try {
      Template template = freemarkerConfig.getTemplate(templatePath);
      try (FileWriter writer = new FileWriter(outputFile)) {
        template.process(data, writer);
      }
      log.info("✅ Generated file: {}", outputFile);
    } catch (Exception e) {
      log.error("❌ Error generating template {} -> {}", templatePath, outputFile, e);
      throw new IOException("Template rendering failed", e);
    }
  }

  @Override
  public void renderCommonTemplates(Map<String, Object> data, File targetDir) throws IOException {
    String[][] templates = {
        {"common/CONTRIBUTING.md.ftl", "CONTRIBUTING.md"},
        {"common/LICENSE.ftl", "LICENSE"},
        {"common/CHANGELOG.md.ftl", "CHANGELOG.md"},
        {"common/README.md.ftl", "README.md"}
    };
    for (String[] tpl : templates) {
      File outFile = new File(targetDir, tpl[1]);
      renderTemplate(tpl[0], data, outFile);
    }
  }
}
