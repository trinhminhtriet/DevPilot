package com.trinhminhtriet.app.cli.skel.command.rust;

import com.moandjiezana.toml.Toml;
import com.trinhminhtriet.app.cli.skel.service.ConfigService;
import com.trinhminhtriet.app.cli.skel.service.TemplateRenderService;
import com.trinhminhtriet.app.cli.skel.service.TomlService;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
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
public class RefactorRustCommand extends AbstractRustCommand implements Runnable {

  @Option(names = {"--dir"}, required = true, description = "Target Rust project directory")
  private File dir;
  private final ConfigService configService;
  private final TomlService tomlService;
  private final TemplateRenderService templateService;

  @Override
  public void run() {
    if (!dir.exists()) {
      dir.mkdirs();
    }

    try {
      log.info("Refactoring Rust project source code in dir={}", dir.getCanonicalPath());

      Map<String, Object> objectMapping = new HashMap<>(configService.loadConfig());

      String projectName = dir.getName();
      objectMapping.put("projectName", projectName);

      renderTemplate(templateService, objectMapping, dir);

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

    var authors = toml.getList("package.authors");
    String oldAuthorName = "";
    String oldAuthorEmail = "";
    if (authors == null || authors.isEmpty()) {
      log.warn("No authors found in Cargo.toml");
    } else {
      for (Object author : authors) {
        String authorStr = author.toString();
        oldAuthorName = authorStr.replaceAll("\\s*<.*?>\\s*", "").trim();
        oldAuthorEmail = authorStr.replaceAll(".*<([^>]+)>.*", "$1").trim();
      }
    }

    Map<String, Object> objectMapping = new HashMap<>(configService.loadConfig());
    Map<String, Object> newAuthor = (HashMap<String, Object>) objectMapping.get("user");

    String[] extensions = {".rs", ".sh", ".yml", ".yaml", ".toml", ".md", ".txt"};

    String newAuthorName = (String) newAuthor.get("name");
    if (!oldAuthorName.isEmpty() && !oldAuthorName.equals(newAuthorName)) {
      String finalOldAuthorName = oldAuthorName;
      replaceInFiles(dir, extensions, (content, file) -> content.replace(finalOldAuthorName, newAuthorName));
      log.info("Replaced author name '{}' with '{}'", finalOldAuthorName, newAuthorName);
    }

    String newAuthorEmail = (String) newAuthor.get("email");
    if (!oldAuthorEmail.isEmpty() && !oldAuthorEmail.equals(newAuthorEmail)) {
      String finalOldAuthorEmail = oldAuthorEmail;
      replaceInFiles(dir, extensions, (content, file) -> content.replace(finalOldAuthorEmail, newAuthorEmail));
      log.info("Replaced author email '{}' with '{}'", finalOldAuthorEmail, newAuthorEmail);
    }

    // TODO: get newLicense from config
    String newLicense = "MIT";//objectMapping.get("license");
    String oldLicense = toml.getString("package.license");
    if (oldLicense != null && !newLicense.equals(oldLicense)) {
      replaceInFiles(dir, extensions, (content, file) -> content.replace(oldLicense, newLicense));
      log.info("Replaced license '{}' with '{}'", oldLicense, newLicense);
    }

    String oldHomepage = toml.getString("package.homepage");
    String newHomepage = "https://trinhminhtriet.com/";
    if (oldHomepage != null && !oldHomepage.equals(newHomepage)) {
      replaceInFiles(dir, extensions, (content, file) -> content.replace(oldHomepage, newHomepage));
      log.info("Replaced homepage '{}' with '{}'", oldHomepage, newHomepage);
    }

    String oldRepository = toml.getString("package.repository");
//    String newRepository = (String) objectMapping.getOrDefault("repository", "");
    String newRepository = "https://github.com/trinhminhtriet/" + dir.getName();
    if (oldRepository != null && !oldRepository.equals(newRepository)) {
      replaceInFiles(dir, extensions, (content, file) -> content.replace(oldRepository, newRepository));
      log.info("Replaced repository '{}' with '{}'", oldRepository, newRepository);
    }

    if (oldRepository != null && oldRepository.contains("github.com")) {
      String[] parts = oldRepository.split("/");
      if (parts.length >= 5) {
        String oldGithubUserName = parts[3];
        String oldGithubRepo = parts[4];

        String newGithubUserName = "trinhminhtriet";
        if (oldGithubUserName != null && !oldGithubUserName.equals(newGithubUserName)) {
          replaceInFiles(dir, extensions, (content, file) -> content.replace(oldGithubUserName, newGithubUserName));
          log.info("Replaced github username '{}' with '{}'", oldGithubUserName, newGithubUserName);
        }

        String newGithubRepo = dir.getName();
        if (oldGithubUserName != null && !oldGithubUserName.equals(newGithubRepo)) {
          replaceInFiles(dir, extensions, (content, file) -> content.replace(oldGithubUserName, newGithubRepo));
          log.info("Replaced github repo name '{}' with '{}'", oldGithubUserName, newGithubRepo);
        }
      }
    }

  }

  private void replaceInFiles(File dir, String[] extensions, BiFunction<String, File, String> replacer) throws IOException {
    File[] files = dir.listFiles((d, name) -> {
      File f = new File(d, name);
      if (!f.isFile()) {
        return false;
      }
      for (String ext : extensions) {
        if (name.endsWith(ext)) {
          return true;
        }
      }
      return false;
    });
    if (files != null) {
      for (File file : files) {
        String content = java.nio.file.Files.readString(file.toPath());
        String newContent = replacer.apply(content, file);
        if (!content.equals(newContent)) {
          java.nio.file.Files.writeString(file.toPath(), newContent);
          log.info("Updated file: {}", file.getName());
        }
      }
    }
  }

}
