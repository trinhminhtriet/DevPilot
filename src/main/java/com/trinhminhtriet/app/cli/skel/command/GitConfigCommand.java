package com.trinhminhtriet.app.cli.skel.command;

import com.trinhminhtriet.app.cli.skel.service.ConfigService;
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
@RequiredArgsConstructor
@Command(name = "config", description = "Set git user.name and user.email in the target directory", mixinStandardHelpOptions = true)
public class GitConfigCommand implements Runnable {

  @Option(names = {"--dir"}, description = "Target directory", defaultValue = ".")
  private File dir;

  @Option(names = {"--name"}, description = "Author name")
  private String authorName;

  @Option(names = {"--email"}, description = "Author email")
  private String authorEmail;
  private final ConfigService configService;

  @Override
  public void run() {
    try {
      if (authorName == null || authorName.isEmpty()) {
        authorName = configService.getValue("user.name");
      }
      if (authorEmail == null || authorEmail.isEmpty()) {
        authorEmail = configService.getValue("user.email");
      }

      if (authorName != null && !authorName.isEmpty()) {
        new ProcessBuilder("git", "config", "user.name", authorName).directory(dir).start().waitFor();
        log.info("Set git user.name to {}", authorName);
      }
      if (authorEmail != null && !authorEmail.isEmpty()) {
        new ProcessBuilder("git", "config", "user.email", authorEmail).directory(dir).start().waitFor();
        log.info("Set git user.email to {}", authorEmail);
      }
    } catch (Exception e) {
      log.error("Failed to set git config: {}", e.getMessage());
    }
  }
}
