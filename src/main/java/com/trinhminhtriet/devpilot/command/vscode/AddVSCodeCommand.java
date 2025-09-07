package com.trinhminhtriet.devpilot.command.vscode;

import com.trinhminhtriet.devpilot.service.TemplateRenderService;
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
@Command(name = "vscode", description = "Add .vscode/settings.json to target directory", mixinStandardHelpOptions = true)
@RequiredArgsConstructor
public class AddVSCodeCommand implements Runnable {

  private final TemplateRenderService templateService;

  @Option(names = {"--dir"}, description = "Target directory", defaultValue = ".")
  private File dir;

  @Override
  public void run() {
    Map<String, Object> objectMapping = new HashMap<>();
    try {
      File vscodeDir = new File(dir, ".vscode");
      if (!vscodeDir.exists() && !vscodeDir.mkdirs()) {
        throw new IllegalStateException("Failed to create .vscode directory: " + vscodeDir);
      }
      templateService.renderTemplate("common/dot-vscode/settings.json.ftl", objectMapping, new File(vscodeDir, "settings.json"));
      log.info(".vscode/settings.json added to {}", vscodeDir.getAbsolutePath());
    } catch (Exception e) {
      log.error("Failed to add .vscode/settings.json", e);
    }
  }
}
