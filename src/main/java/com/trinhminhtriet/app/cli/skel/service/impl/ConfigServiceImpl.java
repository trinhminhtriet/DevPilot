package com.trinhminhtriet.app.cli.skel.service.impl;

import com.trinhminhtriet.app.cli.skel.service.ConfigService;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

@Service
@RequiredArgsConstructor
public class ConfigServiceImpl implements ConfigService {

  @Value("${skel.author.name}")
  private String authorName;

  @Value("${skel.author.email}")
  private String authorEmail;

  @Value("${skel.license.name}")
  private String licenseName;

  private static final String CONFIG_PATH = System.getenv("HOME") + "/.skel/config.yml";
  private final Yaml yaml = new Yaml();


  @Override
  public Map<String, String> loadConfig() {
    File file = new File(CONFIG_PATH);
    if (!file.exists()) {
      // Tạo file config mặc định
      Map<String, String> defaultConfig = new HashMap<>();
      defaultConfig.put("author.name", authorName);
      defaultConfig.put("author.email", authorEmail);
      defaultConfig.put("license.name", licenseName);
      saveConfig(defaultConfig);
      return defaultConfig;
    }
    try {
      String content = new String(Files.readAllBytes(Paths.get(CONFIG_PATH)));
      Map<String, Object> map = yaml.load(content);
      Map<String, String> result = new HashMap<>();
      if (map != null) {
        map.forEach((k, v) -> result.put(k, v == null ? "" : v.toString()));
      }
      return result;
    } catch (IOException e) {
      return new HashMap<>();
    }
  }

  @Override
  public Optional<String> getConfigValue(String key) {
    Map<String, String> config = loadConfig();
    return Optional.ofNullable(config.get(key));
  }

  @Override
  public void saveConfig(Map<String, String> config) {
    File file = new File(CONFIG_PATH);
    file.getParentFile().mkdirs();
    try (FileWriter writer = new FileWriter(file)) {
      yaml.dump(config, writer);
    } catch (IOException e) {
      throw new RuntimeException("Failed to save config", e);
    }
  }
}
