package com.trinhminhtriet.devpilot.service.impl;

import com.moandjiezana.toml.Toml;
import com.trinhminhtriet.devpilot.service.TomlService;
import java.io.File;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class TomlServiceImpl implements TomlService {

  @Override
  public void writeToml(File tomlFile, Map<String, Object> tomlData) {
    StringBuilder sb = new StringBuilder();
    for (Map.Entry<String, Object> entry : tomlData.entrySet()) {
      sb.append(entry.getKey()).append(" = ");
      Object value = entry.getValue();
      if (value instanceof String) {
        sb.append('"').append(value).append('"');
      } else {
        sb.append(value);
      }
      sb.append("\n");
    }
    try {
      java.nio.file.Files.writeString(tomlFile.toPath(), sb.toString());
    } catch (Exception e) {
      throw new RuntimeException("Failed to write TOML file", e);
    }
  }

  @Override
  public Map<String, Object> readToml(File tomlFile) {
    Map<String, Object> result;
    Toml toml = new Toml().read(tomlFile);
    result = toml.toMap();
    return result;
  }
}
