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

class GoProjectActionServiceImplTest {

  private TemplateRenderService templateService;
  private GoProjectActionServiceImpl goProjectActionService;
  private File tempDir;
  private Map<String, Object> config;

  @BeforeEach
  void setUp() throws IOException {
    templateService = mock(TemplateRenderService.class);
    goProjectActionService = new GoProjectActionServiceImpl(templateService);
    tempDir = new File("test-go-project");
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

    goProjectActionService.scaffoldProject("DemoGo", tempDir, config);

    assertTrue(tempDir.exists());
    assertTrue(new File(tempDir, "src").exists());
    assertEquals("DemoGo", config.get("projectName"));

    verify(templateService, times(1)).renderCommonTemplates(eq(config), eq(tempDir));
    verify(templateService, times(1)).renderTemplate(eq("go/gitignore.ftl"), eq(config), any(File.class));
    verify(templateService, times(1)).renderTemplate(eq("go/gitattributes.ftl"), eq(config), any(File.class));
    verify(templateService, times(1)).renderTemplate(eq("go/editorconfig.ftl"), eq(config), any(File.class));
    verify(templateService, times(1)).renderTemplate(eq("go/go.mod.ftl"), eq(config), any(File.class));
    verify(templateService, times(1)).renderTemplate(eq("go/src/main.go.ftl"), eq(config), any(File.class));

  }

  @Test
  void testRefactorProjectLogsInfo() throws Exception {
    // Chỉ kiểm tra không ném lỗi
    goProjectActionService.refactorProject("DemoGo", tempDir, config, "typeA");
  }
}
