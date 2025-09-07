package com.trinhminhtriet.devpilot.command.project;

import com.trinhminhtriet.devpilot.service.ConfigService;
import com.trinhminhtriet.devpilot.service.ProjectActionService;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Component
@RequiredArgsConstructor
@Command(name = "init", description = "Initialize a new project", mixinStandardHelpOptions = true)
@Slf4j
public class ProjectInitCommand implements Runnable {

  @Option(names = "--lang", description = "Project language (rust, go, python, typescript)", required = true)
  private String lang;

  @Option(names = "--name", description = "Project name", required = true)
  private String name;

  private final Map<String, ProjectActionService> projectActionServices;
  private final ConfigService configService;

  @Override
  public void run() {
    String supportedLangs = String.join(", ", projectActionServices.keySet());
    if (!projectActionServices.containsKey(lang.toLowerCase())) {
      System.err.printf("Unsupported language: %s. Supported languages are: %s%n", lang, supportedLangs);
      return;
    }
    String interfaceName = ProjectActionService.class.getSimpleName();
    String implementationName = lang.toLowerCase() + interfaceName + "Impl";
    ProjectActionService service = projectActionServices.get(implementationName);
    if (service == null) {
      System.err.printf("Unsupported language: %s%n", lang);
      return;
    }
    log.info("[%s] Initializing project: %s%n", lang, name);
    try {
      Map<String, Object> objectMapping = new HashMap<>(configService.loadConfig());
      objectMapping.put("projectName", name);

      File dir = new File(name);
      service.scaffoldProject(name, dir, objectMapping);
    } catch (Exception e) {
      System.err.println("Error initializing " + lang + " project: " + e.getMessage());
    }
  }
}
