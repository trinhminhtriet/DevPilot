package com.trinhminhtriet.devpilot.service.impl;

import com.trinhminhtriet.devpilot.service.ConfigService;
import org.junit.jupiter.api.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

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
}
