package com.trinhminhtriet.devpilot.command;

import com.trinhminhtriet.devpilot.service.ConfigService;
import com.trinhminhtriet.devpilot.service.TemplateRenderService;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Slf4j
@Component
@RequiredArgsConstructor
@Command(
    name = "entity",
    description = "Commands for strapi entity",
    mixinStandardHelpOptions = true
)
public class StrapiEntityCommand implements Runnable {

  @Option(names = {"--name"}, required = true, description = "Entity name")
  private String entityName;

  @Option(names = {"--dir"}, required = true, description = "Target directory")
  private File dir;

  private final TemplateRenderService templateService;
  private final ConfigService configService;

  @Override
  public void run() {
    if (!dir.exists() && !dir.mkdirs()) {
      log.error("Failed to create target directory: {}", dir);
      return;
    }

    Map<String, Object> objectMapping = new HashMap<>(configService.loadConfig());
    objectMapping.put("entityName", entityName);
    objectMapping.put("collectionName", entityName.replaceAll("-", "_"));
    objectMapping.put("singularName", entityName);
    objectMapping.put("pluralName", entityName + "s");
    objectMapping.put("displayName", WordUtils.capitalizeFully((entityName.replaceAll("-", " "))));

    log.info("Generating Strapi entity {} in dir={}", entityName, dir);

    generatePart("content-types", "schema.json", "strapi/src/api/entity-name/content-types/schema.json.ftl", objectMapping);
    generatePart("controllers", entityName + ".ts", "strapi/src/api/entity-name/controllers/entity-name.ts.ftl", objectMapping);
    generatePart("routes", entityName + ".ts", "strapi/src/api/entity-name/routes/entity-name.ts.ftl", objectMapping);
    generatePart("services", entityName + ".ts", "strapi/src/api/entity-name/services/entity-name.ts.ftl", objectMapping);

  }

  private void generatePart(String part, String targetFileName, String template, Map<String, Object> objectMapping) {
    File partDir = new File(dir, "src/api/" + entityName + "/" + part);
    if (!partDir.exists() && !partDir.mkdirs()) {
      log.error("Failed to create directory: {}", partDir);
      return;
    }
    File targetFile = new File(partDir, targetFileName);
    templateService.renderTemplate(template, objectMapping, targetFile);
  }
}