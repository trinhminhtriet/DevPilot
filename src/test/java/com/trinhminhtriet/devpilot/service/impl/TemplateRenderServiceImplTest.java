package com.trinhminhtriet.devpilot.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import freemarker.template.Configuration;
import freemarker.template.Template;
import java.io.File;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TemplateRenderServiceImplTest {

  private Configuration configuration;
  private Template template;
  private TemplateRenderServiceImpl service;

  @BeforeEach
  void setUp() throws Exception {
    configuration = mock(Configuration.class);
    template = mock(Template.class);
    service = new TemplateRenderServiceImpl(configuration);
    when(configuration.getTemplate(any(String.class))).thenReturn(template);
  }

  @Test
  void testRenderTemplateSuccess() throws Exception {
    Map<String, Object> data = new HashMap<>();
    data.put("projectName", "TestProject");
    StringWriter writer = new StringWriter();
    doAnswer(invocation -> {
      ((StringWriter) invocation.getArgument(1)).write("Rendered content");
      return null;
    }).when(template).process(eq(data), any(StringWriter.class));

    // Use a temp file for output
    java.io.File tempFile = java.io.File.createTempFile("test", ".md");
    tempFile.deleteOnExit();
    service.renderTemplate("template.ftl", data, new File(tempFile.getAbsolutePath()));
    String result = new String(java.nio.file.Files.readAllBytes(tempFile.toPath()));
    assertEquals("Rendered content", result);
  }

  @Test
  void testRenderTemplateThrowsException() throws Exception {
    when(configuration.getTemplate(any(String.class))).thenThrow(new RuntimeException("Template not found"));
    Map<String, Object> data = new HashMap<>();
    Exception exception = assertThrows(Exception.class, () -> {
      service.renderTemplate("bad.ftl", data, new File("output.md"));
    });
    assertTrue(exception.getMessage().contains("Template rendering failed"));
  }
}
