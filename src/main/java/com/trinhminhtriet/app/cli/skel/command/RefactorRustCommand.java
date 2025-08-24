package com.trinhminhtriet.app.cli.skel.command;

import java.io.File;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Slf4j
@Component
@Command(
    name = "rust",
    description = "Refactor Rust project source code",
    mixinStandardHelpOptions = true
)
public class RefactorRustCommand implements Runnable {

  @Option(names = {"--dir"}, required = true, description = "Target Rust project directory")
  private File dir;

  @Override
  public void run() {
    if (!dir.exists()) {
      dir.mkdirs();
    }

    try {
      log.info("Refactoring Rust project source code in dir={}", dir.getCanonicalPath());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
