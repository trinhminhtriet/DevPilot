package com.trinhminhtriet.app.cli.skel.command;

import com.moandjiezana.toml.Toml;
import com.trinhminhtriet.app.cli.skel.service.ConfigService;
import com.trinhminhtriet.app.cli.skel.service.TomlService;
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
    name = "rust",
    description = "Refactor Rust project source code",
    mixinStandardHelpOptions = true
)
public class RefactorRustCommand implements Runnable {

  @Option(names = {"--dir"}, required = true, description = "Target Rust project directory")
  private File dir;
  private final ConfigService configService;
  private final TomlService tomlService;

  @Override
  public void run() {
    if (!dir.exists()) {
      dir.mkdirs();
    }

    try {
      log.info("Refactoring Rust project source code in dir={}", dir.getCanonicalPath());

      Map<String, Object> objectMapping = new HashMap<>(configService.loadConfig());

      File cargoToml = new File(dir, "Cargo.toml");
      if (!cargoToml.exists()) {
        log.warn("Cargo.toml not found in directory: {}", dir.getCanonicalPath());
        return;
      }

      overrideCargoToml(cargoToml);


    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private void overrideCargoToml(File cargoToml) throws IOException {
    Toml toml = new Toml().read(cargoToml);

    var packageTable = toml.getTable("package");
    var authors = toml.getList("package.authors");
    log.info("Original package.authors={}", authors);
    String oldAuthorName = "";
    String oldAuthorEmail = "";
    if (authors == null || authors.isEmpty()) {
      log.warn("No authors found in Cargo.toml");
    } else {
      for (Object author : authors) {
        String authorStr = author.toString();
        oldAuthorName = authorStr.replaceAll("\\s*<.*?>\\s*", "").trim();
        oldAuthorEmail = authorStr.replaceAll(".*<([^>]+)>.*", "$1").trim();
        log.info("Processing author: name='{}', email='{}'", oldAuthorName, oldAuthorEmail);
      }
    }

    Map<String, Object> objectMapping = new HashMap<>(configService.loadConfig());
    Map<String, Object> newAuthor = (HashMap<String, Object>) objectMapping.get("user");
    String newAuthorName = (String) newAuthor.get("name");
    String newAuthorEmail = (String) newAuthor.get("email");

    File[] files = dir.listFiles((d, name) -> {
      File f = new File(d, name);
      return f.isFile() && (
          name.endsWith(".rs") ||
              name.endsWith(".yml") ||
              name.endsWith(".yaml") ||
              name.endsWith(".toml") ||
              name.endsWith(".md") ||
              name.endsWith(".txt"));
    });
    log.info("Files to process in {}: {}", dir.getCanonicalPath(), files != null ? files.length : 0);
    if (files != null) {
      for (File file : files) {
        String content = java.nio.file.Files.readString(file.toPath());
        content = content.replace(oldAuthorName, newAuthorName)
            .replace(oldAuthorEmail, newAuthorEmail);
        java.nio.file.Files.writeString(file.toPath(), content);
        log.info("Updated author info in file: {}", file.getName());
      }
    }

    if (packageTable == null) {
      log.warn("No [package] section found in Cargo.toml");
    }
  }

}
