package com.trinhminhtriet.devpilot.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.trinhminhtriet.devpilot.service.TemplateRenderService;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JavaProjectActionServiceImplTest {

  private TemplateRenderService templateService;
  private JavaProjectActionServiceImpl javaService;
  private File tempDir;
  private Map<String, Object> config;

  @BeforeEach
  void setUp() throws IOException {
    templateService = mock(TemplateRenderService.class);
    javaService = new JavaProjectActionServiceImpl(templateService);
    tempDir = new File("test-java-project");
    if (tempDir.exists()) {
      FileUtils.deleteDirectory(tempDir);
    }
    config = new HashMap<>();
  }

  @AfterEach
  void cleanup() throws IOException {
    if (tempDir.exists()) {
      FileUtils.deleteDirectory(tempDir);
    }
  }

  @Test
  void testScaffoldProjectCreatesFilesAndDirs() throws Exception {
    doNothing().when(templateService).renderCommonTemplates(any(), any());
    doNothing().when(templateService).renderTemplate(any(), any(), any());

    javaService.scaffoldProject("DemoJava", tempDir, config);

    assertTrue(tempDir.exists());
    assertTrue(new File(tempDir, "src").exists());
    assertEquals("DemoJava", config.get("projectName"));
    verify(templateService, times(1)).renderCommonTemplates(eq(config), eq(tempDir));
  }

  @Test
  void testRefactorProjectLogsInfo() throws Exception {
    javaService.refactorProject("DemoJava", tempDir, config, "typeA");
  }
}
