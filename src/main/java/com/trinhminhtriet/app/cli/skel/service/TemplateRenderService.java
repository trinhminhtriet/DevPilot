package com.trinhminhtriet.app.cli.skel.service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public interface TemplateRenderService {

  void renderTemplate(String templatePath, Map<String, Object> data, File outputFile) throws IOException;
}
