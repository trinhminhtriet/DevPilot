package com.trinhminhtriet.devpilot.command.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(
    name = "diff",
    description = "Compare multiple .env files and highlight differences in environment variable names and values",
    mixinStandardHelpOptions = true
)
public class EnvDiffCommand implements Runnable {

  @Option(names = {"--dir", "-d"}, description = "Directory to scan for .env files", defaultValue = ".")
  File dir;

  @Option(names = "--csv", description = "Output differences as CSV")
  boolean csv;

  @Parameters(arity = "0..*", description = "Specific .env files to compare")
  List<File> files;

  @Override
  public void run() {
    try {
      List<File> envFiles = files != null && !files.isEmpty() ? files : scanEnvFiles(dir);
      if (envFiles.isEmpty()) {
        System.err.println("No .env files found.");
        System.exit(1);
      }
      Map<String, Map<String, String>> envMaps = new LinkedHashMap<>();
      for (File f : envFiles) {
        envMaps.put(f.getName(), parseEnvFile(f));
      }
      Set<String> allKeys = envMaps.values().stream()
          .flatMap(m -> m.keySet().stream())
          .collect(Collectors.toCollection(TreeSet::new));
      boolean hasDiff = false;
      List<String> envNames = new ArrayList<>(envMaps.keySet());
      List<List<String>> table = new ArrayList<>();
      table.add(new ArrayList<>());
      table.get(0).add("Variable");
      table.get(0).addAll(envNames);
      for (String key : allKeys) {
        List<String> row = new ArrayList<>();
        row.add(key);
        String refValue = null;
        boolean allMatch = true;
        for (String env : envNames) {
          Map<String, String> map = envMaps.get(env);
          if (!map.containsKey(key)) {
            row.add("MISSING");
            hasDiff = true;
            allMatch = false;
          } else {
            String val = map.get(key);
            if (refValue == null) {
              refValue = val;
            }
            if (Objects.equals(refValue, val)) {
              row.add("OK");
            } else {
              row.add("DIFF");
              hasDiff = true;
              allMatch = false;
            }
          }
        }
        table.add(row);
      }
      if (csv) {
        printCsv(table);
      } else {
        printTable(table);
      }
      System.exit(hasDiff ? 1 : 0);
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
      System.exit(1);
    }
  }

  private List<File> scanEnvFiles(File dir) {
    try {
      return Files.list(dir.toPath())
          .map(Path::toFile)
          .filter(f -> f.isFile() && f.getName().matches(".*\\.env.*"))
          .collect(Collectors.toList());
    } catch (IOException e) {
      return List.of();
    }
  }

  private Map<String, String> parseEnvFile(File file) {
    Map<String, String> map = new LinkedHashMap<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      String line;
      while ((line = reader.readLine()) != null) {
        line = line.trim();
        if (line.isEmpty() || line.startsWith("#")) {
          continue;
        }
        int idx = line.indexOf('=');
        if (idx > 0) {
          String key = line.substring(0, idx).trim();
          String value = line.substring(idx + 1).trim();
          map.put(key, value);
        }
      }
    } catch (IOException ignored) {
    }
    return map;
  }

  private void printTable(List<List<String>> table) {
    // Markdown table output
    List<String> header = table.get(0);
    int cols = header.size();
    int[] widths = new int[cols];
    for (List<String> row : table) {
      for (int i = 0; i < cols; i++) {
        widths[i] = Math.max(widths[i], row.get(i).length());
      }
    }
    // Header
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < cols; i++) {
      sb.append("| ").append(pad(header.get(i), widths[i])).append(" ");
    }
    sb.append("|");
    System.out.println(sb);
    // Separator
    sb.setLength(0);
    for (int i = 0; i < cols; i++) {
      sb.append("|-").append("-".repeat(widths[i])).append("-");
    }
    sb.append("|");
    System.out.println(sb);
    // Rows
    for (int r = 1; r < table.size(); r++) {
      sb.setLength(0);
      List<String> row = table.get(r);
      for (int i = 0; i < cols; i++) {
        sb.append("| ").append(pad(row.get(i), widths[i])).append(" ");
      }
      sb.append("|");
      System.out.println(sb);
    }
  }

  private String pad(String s, int w) {
    return String.format("%-" + w + "s", s);
  }

  private void printCsv(List<List<String>> table) {
    for (List<String> row : table) {
      System.out.println(String.join(",", row));
    }
  }
}