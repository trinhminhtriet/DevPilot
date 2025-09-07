package com.trinhminhtriet.app.cli.skel.command.file;

import com.trinhminhtriet.app.cli.skel.service.ConfigService;
import com.trinhminhtriet.app.cli.skel.service.TemplateRenderService;
import java.io.File;
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
    try {
      templateService.renderTemplate("common/README.md.ftl", objectMapping, new File(dir, "README.md"));
      log.info("README.md added to {}", dir.getAbsolutePath());
    } catch (Exception e) {
      log.error("Failed to add README.md", e);
    }
  }
}
