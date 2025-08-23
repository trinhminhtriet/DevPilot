package com.trinhminhtriet.app.cli.skel.service.impl;

import com.trinhminhtriet.app.cli.skel.service.TemplateRenderService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

@Log4j2
@Service
@RequiredArgsConstructor
public class TemplateRenderServiceImpl implements TemplateRenderService {

  private final Configuration freemarkerConfig;

  @Override
  public void renderTemplate(String templatePath, Map<String, Object> data, String outputFile) throws IOException {
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
}
