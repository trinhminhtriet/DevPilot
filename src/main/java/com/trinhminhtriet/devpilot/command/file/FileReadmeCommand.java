package com.trinhminhtriet.devpilot.command.file;

import com.github.lalyos.jfiglet.FigletFont;
import com.trinhminhtriet.devpilot.service.ConfigService;
import com.trinhminhtriet.devpilot.service.TemplateRenderService;
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
@Command(name = "readme", description = "Add README.md to current directory", mixinStandardHelpOptions = true)
@RequiredArgsConstructor
public class FileReadmeCommand implements Runnable {

  private final TemplateRenderService templateService;

  @Option(names = {"--dir"}, description = "Target directory", defaultValue = ".")
  private File dir;

  private final ConfigService configService;

  @Override
  public void run() {
    Map<String, Object> objectMapping = new HashMap<>(configService.loadConfig());
    String projectName = dir.getName();
    objectMapping.put("projectName", projectName);
    String projectNameFiglet = null;
    try {
      projectNameFiglet = FigletFont.convertOneLine(projectName);
      objectMapping.put("projectNameFiglet", projectNameFiglet);
      log.info(projectNameFiglet);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    templateService.renderTemplate("common/README.md.ftl", objectMapping, new File(dir, "README.md"));

  }
}
