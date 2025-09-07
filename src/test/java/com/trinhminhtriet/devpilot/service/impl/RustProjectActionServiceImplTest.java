package com.trinhminhtriet.devpilot.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

import com.trinhminhtriet.devpilot.service.ConfigService;
import com.trinhminhtriet.devpilot.service.TemplateRenderService;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RustProjectActionServiceImplTest {

  private TemplateRenderService templateService;
  private com.trinhminhtriet.devpilot.service.ConfigService configService;
  private RustProjectActionServiceImpl rustService;
  private File tempDir;
  private Map<String, Object> config;

  @BeforeEach
  void setUp() {
    templateService = mock(TemplateRenderService.class);
    configService = mock(ConfigService.class);
    rustService = new RustProjectActionServiceImpl(templateService, configService);
    tempDir = new File("test-rust-project");
    if (tempDir.exists()) {
      tempDir.delete();
    }

    config = new HashMap<>(configService.loadConfig());
    config.put("projectName", "DemoRust");
  }

  @AfterEach
  void cleanup() {
    if (tempDir.exists()) {
      tempDir.delete();
    }
  }

  @Test
  void testScaffoldProjectCreatesFilesAndDirs() throws Exception {
    doNothing().when(templateService).renderCommonTemplates(any(), any());
    doNothing().when(templateService).renderTemplate(any(), any(), any());

    rustService.scaffoldProject("DemoRust", tempDir, config);

    assertTrue(tempDir.exists());
    assertTrue(new File(tempDir, "src").exists());
    assertEquals("DemoRust", config.get("projectName"));
//    verify(templateService, times(1)).renderCommonTemplates(eq(config), eq(tempDir));

  }

  @Test
  void testRefactorProjectLogsInfo() throws Exception {
    rustService.refactorProject("DemoRust", tempDir, config, "typeA");
  }
}
