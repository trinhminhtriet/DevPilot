package com.trinhminhtriet.devpilot.command.scan;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import picocli.CommandLine;

@CommandLine.Command(
    name = "secret",
    description = "Scan source code for secrets (passwords, tokens, API keys, ...)",
    mixinStandardHelpOptions = true
)
public class ScanSecretCommand implements Runnable {

  @CommandLine.Option(names = {"--path", "-p"}, description = "Path to scan", defaultValue = ".")
  private File path;

  @CommandLine.Option(names = {"--exclude", "-e"}, description = "Exclude pattern (glob)")
  private String exclude;

  @CommandLine.Option(names = {"--report", "-r"}, description = "Output report file")
  private File reportFile;

  @Override
  public void run() {
    Pattern secretPattern = Pattern.compile(
        "(password|passwd|token|api[_-]?key|secret|PRIVATE_KEY|AWS_SECRET_ACCESS_KEY)[\"'=:\s]+[\\w\\-\\+/=]{8,}",
        Pattern.CASE_INSENSITIVE
    );
    List<File> files = FileWalker.getAllSourceFiles(path, exclude);
    StringBuilder report = new StringBuilder();
    for (File file : files) {
      try {
        List<String> lines = Files.readAllLines(file.toPath());
        for (int i = 0; i < lines.size(); i++) {
          Matcher m = secretPattern.matcher(lines.get(i));
          if (m.find()) {
            String found = String.format("%s:%d: %s", file.getPath(), i + 1, lines.get(i).trim());
            System.out.println(found);
            report.append(found).append("\n");
          }
        }
      } catch (Exception ignored) {
      }
    }
    if (reportFile != null) {
      try {
        Files.writeString(reportFile.toPath(), report.toString());
        System.out.println("Report written to " + reportFile.getPath());
      } catch (Exception e) {
        System.err.println("Failed to write report: " + e.getMessage());
      }
    }
  }
}

// Utility class to walk files
class FileWalker {

  public static List<File> getAllSourceFiles(File dir, String excludePattern) {
    // TODO: Implement recursive file listing, filter by excludePattern (glob)
    // For now, return all .java, .py, .js, .ts, .go, .rs files
    try {
      return Files.walk(dir.toPath())
          .filter(p -> {
            String name = p.getFileName().toString();
            return name.endsWith(".java") || name.endsWith(".py") || name.endsWith(".js") || name.endsWith(".ts") || name.endsWith(".go") || name.endsWith(".rs");
          })
          .map(Path::toFile)
          .toList();
    } catch (Exception e) {
      return List.of();
    }
  }
}
