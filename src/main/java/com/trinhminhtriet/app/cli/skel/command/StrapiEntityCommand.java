package com.trinhminhtriet.app.cli.skel.command;

import com.trinhminhtriet.app.cli.skel.service.ConfigService;
import com.trinhminhtriet.app.cli.skel.service.TemplateRenderService;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    try {
      if (!dir.exists()) {
        dir.mkdirs();
      }

      Map<String, Object> objectMapping = new HashMap<>(configService.loadConfig());
      objectMapping.put("entityName", entityName);

      log.info("Generating Strapi entity {} in dir={}", entityName, dir);

      File contentTypesDir = new File(dir, "src/api/" + entityName + "/content-types");
      if (!contentTypesDir.exists()) {
        contentTypesDir.mkdirs();
      }
      templateService.renderTemplate("strapi/src/api/entity-name/content-types/schema.json.ftl", objectMapping, new File(contentTypesDir, "schema.json"));

      File controllersDir = new File(dir, "src/api/" + entityName + "/controllers");
      if (!controllersDir.exists()) {
        controllersDir.mkdirs();
      }
      templateService.renderTemplate(
          "strapi/src/api/entity-name/controllers/entity-name.ts.ftl",
          objectMapping, new File(controllersDir, entityName + ".ts"));

      File routesDir = new File(dir, "src/api/" + entityName + "/routes");
      if (!routesDir.exists()) {
        routesDir.mkdirs();
      }
      templateService.renderTemplate(
          "strapi/src/api/entity-name/routes/entity-name.ts.ftl",
          objectMapping, new File(routesDir, entityName + ".ts"));

      File servicesDir = new File(dir, "src/api/" + entityName + "/services");
      if (!servicesDir.exists()) {
        servicesDir.mkdirs();
      }
      templateService.renderTemplate(
          "strapi/src/api/entity-name/services/entity-name.ts.ftl",
          objectMapping, new File(servicesDir, entityName + ".ts"));

    } catch (IOException e) {
      log.error("Error generating Strapi entity {} -> {}", entityName, dir, e);
    }
  }
}
