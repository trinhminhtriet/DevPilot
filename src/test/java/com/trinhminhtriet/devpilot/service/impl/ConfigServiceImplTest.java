package com.trinhminhtriet.devpilot.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.trinhminhtriet.devpilot.service.ConfigService;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConfigServiceImplTest {

  private ConfigService configService;
  private String configPath;

  @BeforeEach
  void setUp() {
    configService = new ConfigServiceImpl();
    configPath = configService.getConfigFilePath();
    // Xóa file config trước mỗi test
    File file = new File(configPath);
    if (file.exists()) {
      file.delete();
    }
  }

  @Test
  void testLoadConfigCreatesDefaultIfNotExists() {
    Map<String, Object> config = configService.loadConfig();
    assertEquals("MIT", config.get("license"));
    assertTrue(new File(configPath).exists());
  }

  @Test
  void testSaveAndLoadConfig() {
    Map<String, Object> config = new HashMap<>();
    config.put("author", "Alice");
    configService.saveConfig(config);

    Map<String, Object> loaded = configService.loadConfig();
    assertEquals("Alice", loaded.get("author"));
  }

  @Test
  void testGetValueExistingKey() {
    Map<String, Object> config = new HashMap<>();
    config.put("user", Map.of("name", "Bob"));
    configService.saveConfig(config);

    String value = configService.getValue("user.name");
    assertEquals("Bob", value);
  }

  @Test
  void testGetValueNonExistingKey() {
    Map<String, Object> config = new HashMap<>();
    configService.saveConfig(config);

    String value = configService.getValue("not.exist");
    assertNull(value);
  }

  @Test
  void testGetConfigFilePath() {
    String path = configService.getConfigFilePath();
    assertTrue(path.endsWith("config.yml"));
  }

  @Test
  void testInitConfigCopiesTemplate() throws Exception {
    File configFile = new File(configPath);
    assertFalse(configFile.exists());

    configService.initConfig();
    assertTrue(configFile.exists());

    String configContent = Files.readString(Paths.get(configPath));
    String templateContent = Files.readString(Paths.get("src/main/resources/templates/config.yml"));
    assertEquals(templateContent.trim(), configContent.trim());
  }
}
