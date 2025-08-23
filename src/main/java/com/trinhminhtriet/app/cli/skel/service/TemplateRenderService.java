package com.trinhminhtriet.app.cli.skel.service;

import java.io.IOException;
import java.util.Map;

public interface TemplateRenderService {

  void renderTemplate(String templatePath, Map<String, Object> data, String outputFile) throws IOException;
}
