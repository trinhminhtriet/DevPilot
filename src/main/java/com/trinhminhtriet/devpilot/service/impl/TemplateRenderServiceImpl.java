package com.trinhminhtriet.devpilot.service.impl;

import com.github.lalyos.jfiglet.FigletFont;
import com.trinhminhtriet.devpilot.service.TemplateRenderService;
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
  public void renderTemplate(String templatePath, Map<String, Object> dataMapping, File outputFile) {
    try {
      Template template = freemarkerConfig.getTemplate(templatePath);
      try (FileWriter writer = new FileWriter(outputFile)) {
        template.process(dataMapping, writer);
      }
      log.info("✅ Generated file: {}", outputFile);
    } catch (Exception e) {
      log.error("❌ Error generating template {} -> {}", templatePath, outputFile, e);
    }
  }

  @Override
  public void renderCommonTemplates(Map<String, Object> objectMapping, File targetDir) throws IOException {
    String projectName = objectMapping.get("projectName").toString();
    String projectNameFiglet = FigletFont.convertOneLine(projectName);

    log.info(projectNameFiglet);

    objectMapping.put("projectNameFiglet", projectNameFiglet);

    String[][] templates = {
        {"common/README.md.ftl", "README.md"},
        {"common/CHANGELOG.md.ftl", "CHANGELOG.md"},
        {"common/CONTRIBUTING.md.ftl", "CONTRIBUTING.md"},
        {"common/CODE_OF_CONDUCT.md.ftl", "CODE_OF_CONDUCT.md"},
        {"common/SECURITY.md.ftl", "SECURITY.md"},
        {"common/LICENSE.ftl", "LICENSE"}
    };
    for (String[] tpl : templates) {
      File outFile = new File(targetDir, tpl[1]);
      renderTemplate(tpl[0], objectMapping, outFile);
    }
  }
}
