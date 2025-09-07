package com.trinhminhtriet.app.cli.skel.command.file;

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
@Command(name = "gitattributes", description = "Add .gitattributes to current directory", mixinStandardHelpOptions = true)
@RequiredArgsConstructor
public class FileGitAttributesCommand implements Runnable {

  private final TemplateRenderService templateService;

  @Option(names = {"--dir"}, description = "Target directory", defaultValue = ".")
  private File dir;

  @Override
  public void run() {
    Map<String, Object> objectMapping = new HashMap<>();
    try {
      templateService.renderTemplate("common/gitattributes.ftl", objectMapping, new File(dir, ".gitattributes"));
      log.info(".gitattributes added to {}", dir.getAbsolutePath());
    } catch (Exception e) {
      log.error("Failed to add .gitattributes", e);
    }
  }
}
