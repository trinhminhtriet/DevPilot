package com.trinhminhtriet.app.cli.skel.service.impl;

import com.trinhminhtriet.app.cli.skel.service.ConfigService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfigServiceImpl implements ConfigService {

  @Value("${skel.user.name:}")
  private String authorName;

  @Value("${skel.user.email:}")
  private String authorEmail;

  @Value("${skel.license.name:MIT}")
  private String licenseName;

  private static final String CONFIG_PATH = System.getProperty("user.home") + "/.skel/config.properties";

  @Override
  public Map<String, Object> loadConfig() {
    File file = new File(CONFIG_PATH);
    Properties props = new Properties();
    if (!file.exists()) {
      props.setProperty("license.name", licenseName != null ? licenseName : "MIT");
      saveConfig(propertiesToNestedMap(props));
    } else {
      try (FileInputStream fis = new FileInputStream(file)) {
        props.load(fis);
      } catch (IOException e) {
        // ignore, return empty
      }
    }
    return propertiesToNestedMap(props);
  }

  /**
   * Helper to set nested value in map for key like a.b.c
   */
  private void setNestedValue(Map<String, Object> map, String key, String value) {
    String[] parts = key.split("\\.");
    Map<String, Object> current = map;
    for (int i = 0; i < parts.length - 1; i++) {
      current = (Map<String, Object>) current.computeIfAbsent(parts[i], k -> new HashMap<>());
    }
    current.put(parts[parts.length - 1], value);
  }

  private Map<String, Object> propertiesToNestedMap(Properties props) {
    Map<String, Object> nested = new HashMap<>();
    for (String key : props.stringPropertyNames()) {
      setNestedValue(nested, key, props.getProperty(key));
    }
    return nested;
  }

  private void flattenMap(String prefix, Map<String, Object> map, Properties props) {
    for (Map.Entry<String, Object> entry : map.entrySet()) {
      String key = prefix.isEmpty() ? entry.getKey() : prefix + "." + entry.getKey();
      Object value = entry.getValue();
      if (value instanceof Map) {
        flattenMap(key, (Map<String, Object>) value, props);
      } else {
        props.setProperty(key, value != null ? value.toString() : "");
      }
    }
  }

  @Override
  public void saveConfig(Map<String, Object> config) {
    File file = new File(CONFIG_PATH);
    file.getParentFile().mkdirs();
    Properties props = new Properties();
    flattenMap("", config, props);
    try (FileOutputStream fos = new FileOutputStream(file)) {
      props.store(fos, "Skel CLI config");
    } catch (IOException e) {
      throw new RuntimeException("Failed to save config", e);
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
