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
  void testRenderTemplateThrowsException() throws Exception {
    when(configuration.getTemplate(any(String.class))).thenThrow(new RuntimeException("Template rendering failed"));
    Map<String, Object> data = new HashMap<>();
    Exception exception = assertThrows(Exception.class, () -> {
      service.renderTemplate("bad.ftl", data, new File("output.md"));
    });
    assertTrue(exception.getMessage().contains("Template rendering failed"));
  }

  @Test
  void testRenderTemplateSuccess() throws Exception {
    Map<String, Object> data = new HashMap<>();
    File outputFile = new File("output-success.md");
    doAnswer(invocation -> {
      // Giả lập việc ghi file thành công
      Object model = invocation.getArgument(0);
      Object writer = invocation.getArgument(1);
      ((java.io.Writer) writer).write("Rendered content");
      return null;
    }).when(template).process(any(), any());

    service.renderTemplate("good.ftl", data, outputFile);
    // Kiểm tra file đã được ghi
    assertTrue(outputFile.exists());
    String content = new String(java.nio.file.Files.readAllBytes(outputFile.toPath()));
    assertEquals("Rendered content", content);
    // Dọn dẹp
    outputFile.delete();
  }

  
}
