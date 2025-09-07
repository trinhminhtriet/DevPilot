package com.trinhminhtriet.devpilot.service.impl;

import com.trinhminhtriet.devpilot.service.TemplateRenderService;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PythonProjectActionServiceImplTest {
    private TemplateRenderService templateService;
    private PythonProjectActionServiceImpl pythonService;
    private File tempDir;
    private Map<String, Object> config;

    @BeforeEach
    void setUp() {
        templateService = mock(TemplateRenderService.class);
        pythonService = new PythonProjectActionServiceImpl(templateService);
        tempDir = new File("test-python-project");
        if (tempDir.exists()) tempDir.delete();
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

        pythonService.scaffoldProject("DemoPython", tempDir, config);

        assertTrue(tempDir.exists());
        assertTrue(new File(tempDir, "src").exists());
        assertEquals("DemoPython", config.get("projectName"));
        verify(templateService, times(1)).renderCommonTemplates(eq(config), eq(tempDir));

    }

    @Test
    void testRefactorProjectLogsInfo() throws Exception {
        pythonService.refactorProject("DemoPython", tempDir, config, "typeA");
    }
}
