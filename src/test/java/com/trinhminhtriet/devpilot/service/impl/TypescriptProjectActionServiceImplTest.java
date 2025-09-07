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

class TypescriptProjectActionServiceImplTest {

  private TemplateRenderService templateService;
  private TypescriptProjectActionServiceImpl tsService;
  private File tempDir;
  private Map<String, Object> config;

  @BeforeEach
  void setUp() throws IOException {
    templateService = mock(TemplateRenderService.class);
    tsService = new TypescriptProjectActionServiceImpl(templateService);
    tempDir = new File("test-ts-project");
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

    tsService.scaffoldProject("DemoTS", tempDir, config);

    assertTrue(tempDir.exists());
    assertTrue(new File(tempDir, "src").exists());
    assertEquals("DemoTS", config.get("projectName"));
    verify(templateService, times(1)).renderCommonTemplates(eq(config), eq(tempDir));
  }

  @Test
  void testRefactorProjectLogsInfo() throws Exception {
    tsService.refactorProject("DemoTS", tempDir, config, "typeA");
  }
}
