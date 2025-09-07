package com.trinhminhtriet.devpilot.service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public interface TemplateRenderService {

  /**
   * Renders a single template file with the given data model and writes the result to the specified output file.
   *
   * @param templatePath The path to the template file
   * @param dataMapping  The data model to merge with the template
   * @param outputFile   The file to write the rendered output to
   */
  void renderTemplate(String templatePath, Map<String, Object> dataMapping, File outputFile);

  /**
   * Render common templates (README.md, LICENSE, ...)
   *
   * @param dataMapping The data model to merge with the template
   * @param targetDir   Target directory
   */
  void renderCommonTemplates(Map<String, Object> dataMapping, File targetDir) throws IOException;
}
