package com.trinhminhtriet.app.cli.skel.service.impl;

import com.trinhminhtriet.app.cli.skel.service.ConfigService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfigServiceImpl implements ConfigService {

  private static final String CONFIG_PATH = System.getProperty("user.home") + "/.skel/config.yml";

  @Override
  public Map<String, Object> loadConfig() {
    File file = new File(CONFIG_PATH);
    Map<String, Object> config = new HashMap<>();
    if (!file.exists()) {
      config.put("license", "MIT");
      saveConfig(config);
    } else {
      try (FileInputStream fis = new FileInputStream(file)) {
        org.yaml.snakeyaml.Yaml yaml = new org.yaml.snakeyaml.Yaml();
        Object loaded = yaml.load(fis);
        if (loaded instanceof Map) {
          config = (Map<String, Object>) loaded;
        }
      } catch (IOException e) {
        // ignore, return empty
      }
    }
    return config;
  }

  @Override
  public void saveConfig(Map<String, Object> config) {
    File file = new File(CONFIG_PATH);
    file.getParentFile().mkdirs();
    try (FileOutputStream fos = new FileOutputStream(file)) {
      org.yaml.snakeyaml.Yaml yaml = new org.yaml.snakeyaml.Yaml();
      yaml.dump(config, new java.io.OutputStreamWriter(fos));
    } catch (IOException e) {
      throw new RuntimeException("Failed to save config to " + CONFIG_PATH, e);
    }
  }

  @Override
  public String getValue(String key) {
    Map<String, Object> config = loadConfig();
    String[] parts = key.split("\\.");
    Object current = config;
    for (String part : parts) {
      if (current instanceof Map) {
        current = ((Map<?, ?>) current).get(part);
      } else {
        return null;
      }
      if (current == null) {
        return null;
      }
    }
    return current != null ? current.toString() : null;
  }
}
