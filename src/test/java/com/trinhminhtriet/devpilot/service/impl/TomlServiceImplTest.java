package com.trinhminhtriet.devpilot.service.impl;

import org.junit.jupiter.api.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class TomlServiceImplTest {
    private TomlServiceImpl tomlService;
    private File tempFile;

    @BeforeEach
    void setUp() {
        tomlService = new TomlServiceImpl();
        tempFile = new File("test-toml-file.toml");
        if (tempFile.exists()) tempFile.delete();
    }

    @AfterEach
    void cleanup() {
        if (tempFile.exists()) tempFile.delete();
    }

    @Test
    void testWriteAndReadToml() {
        Map<String, Object> data = new HashMap<>();
        data.put("name", "Alice");
        data.put("age", 30);
        data.put("active", true);
        tomlService.writeToml(tempFile, data);
        assertTrue(tempFile.exists());
        Map<String, Object> read = tomlService.readToml(tempFile);
        assertEquals("Alice", read.get("name"));
        assertEquals(30L, read.get("age")); // toml4j parses numbers as Long
        assertEquals(true, read.get("active"));
    }

    @Test
    void testWriteTomlWithStringEscaping() {
        Map<String, Object> data = new HashMap<>();
        data.put("desc", "Hello, world!");
        tomlService.writeToml(tempFile, data);
        Map<String, Object> read = tomlService.readToml(tempFile);
        assertEquals("Hello, world!", read.get("desc"));
    }

    @Test
    void testReadTomlFileNotExist() {
        File notExist = new File("not-exist.toml");
        assertThrows(Exception.class, () -> tomlService.readToml(notExist));
    }
}
