package com.trinhminhtriet.devpilot.service.impl;

import com.trinhminhtriet.devpilot.service.ConfigService;
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

  private static final String CONFIG_PATH = System.getProperty("user.home") + "/.devpilot/config.yml";
  private static final String TEMPLATE_CONFIG_PATH = "src/main/resources/templates/config.yml";

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
  public void initConfig() {
    File configFile = new File(CONFIG_PATH);
    if (!configFile.exists()) {
      File templateFile = new File(TEMPLATE_CONFIG_PATH);
      configFile.getParentFile().mkdirs();
      try (FileInputStream fis = new FileInputStream(templateFile);
           FileOutputStream fos = new FileOutputStream(configFile)) {
        byte[] buffer = new byte[1024];
        int length;
        while ((length = fis.read(buffer)) > 0) {
          fos.write(buffer, 0, length);
        }
      } catch (IOException e) {
        throw new RuntimeException("Failed to initialize config from template", e);
      }
    }
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

  @Override
  public String getConfigFilePath() {
    String path = new File(CONFIG_PATH).getAbsolutePath();
    if (File.separatorChar == '\\') { // Windows
      path = path.replace("/", "\\");
    } else { // Unix/Linux/Mac
      path = path.replace("\\", "/");
    }
    return path;
  }
}
