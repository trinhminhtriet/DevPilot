package com.trinhminhtriet.devpilot.service.impl;

import com.trinhminhtriet.devpilot.service.TemplateRenderService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TypescriptProjectActionServiceImplTest {
    private TemplateRenderService templateService;
    private TypescriptProjectActionServiceImpl tsService;
    private File tempDir;
    private Map<String, Object> config;

    @BeforeEach
    void setUp() {
        templateService = mock(TemplateRenderService.class);
        tsService = new TypescriptProjectActionServiceImpl(templateService);
        tempDir = new File("test-ts-project");
        if (tempDir.exists()) tempDir.delete();
        config = new HashMap<>();
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
