package com.trinhminhtriet.devpilot.service.impl;

import com.trinhminhtriet.devpilot.service.ProjectActionService;
import com.trinhminhtriet.devpilot.service.TemplateRenderService;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class JavaProjectActionServiceImpl implements ProjectActionService {

  private final TemplateRenderService templateService;

  @Override
  public void scaffoldProject(String projectName, File dir, Map<String, Object> config) throws IOException {
    if (!dir.exists()) {
      dir.mkdirs();
    }
    config.put("projectName", projectName);
    // Generate Maven structure
    File srcMainJava = new File(dir, "src/main/java");
    File srcMainResources = new File(dir, "src/main/resources");
    File srcTestJava = new File(dir, "src/test/java");
    srcMainJava.mkdirs();
    srcMainResources.mkdirs();
    srcTestJava.mkdirs();
    // Generate Gradle structure
    File buildGradle = new File(dir, "build.gradle");
    File settingsGradle = new File(dir, "settings.gradle");
    if (!buildGradle.exists()) {
      buildGradle.createNewFile();
      java.nio.file.Files.writeString(buildGradle.toPath(), "plugins {\n    id 'java'\n}\n\ngroup = 'com.example'\nversion = '1.0.0'\n\nrepositories {\n    mavenCentral()\n}\n\ndependencies {\n    testImplementation 'org.junit.jupiter:junit-jupiter:5.8.1'\n}\n\ntest {\n    useJUnitPlatform()\n}\n");
    }
    if (!settingsGradle.exists()) {
      settingsGradle.createNewFile();
      java.nio.file.Files.writeString(settingsGradle.toPath(), "rootProject.name = '" + projectName + "'\n");
    }
    // Generate Maven pom.xml
    File pomXml = new File(dir, "pom.xml");
    if (!pomXml.exists()) {
      pomXml.createNewFile();
      java.nio.file.Files.writeString(pomXml.toPath(), "<project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n  <modelVersion>4.0.0</modelVersion>\n  <groupId>com.example</groupId>\n  <artifactId>" + projectName + "</artifactId>\n  <version>1.0.0</version>\n  <properties>\n    <maven.compiler.source>17</maven.compiler.source>\n    <maven.compiler.target>17</maven.compiler.target>\n  </properties>\n  <dependencies>\n    <dependency>\n      <groupId>org.junit.jupiter</groupId>\n      <artifactId>junit-jupiter</artifactId>\n      <version>5.8.1</version>\n      <scope>test</scope>\n    </dependency>\n  </dependencies>\n</project>\n");
    }
    // Add code quality plugins to pom.xml
    if (pomXml.exists()) {
      String pomContent = java.nio.file.Files.readString(pomXml.toPath());
      String plugins = "<build>\n" +
        "  <plugins>\n" +
        "    <plugin>\n" +
        "      <groupId>org.apache.maven.plugins</groupId>\n" +
        "      <artifactId>maven-checkstyle-plugin</artifactId>\n" +
        "      <version>3.2.1</version>\n" +
        "      <configuration>\n" +
        "        <configLocation>google_checks.xml</configLocation>\n" +
        "      </configuration>\n" +
        "    </plugin>\n" +
        "    <plugin>\n" +
        "      <groupId>org.apache.maven.plugins</groupId>\n" +
        "      <artifactId>maven-pmd-plugin</artifactId>\n" +
        "      <version>3.15.0</version>\n" +
        "    </plugin>\n" +
        "    <plugin>\n" +
        "      <groupId>org.jacoco</groupId>\n" +
        "      <artifactId>jacoco-maven-plugin</artifactId>\n" +
        "      <version>0.8.10</version>\n" +
        "    </plugin>\n" +
        "    <plugin>\n" +
        "      <groupId>org.apache.maven.plugins</groupId>\n" +
        "      <artifactId>maven-surefire-plugin</artifactId>\n" +
        "      <version>3.1.2</version>\n" +
        "    </plugin>\n" +
        "  </plugins>\n" +
        "</build>\n";
      if (!pomContent.contains("maven-checkstyle-plugin")) {
        pomContent = pomContent.replace("</project>", plugins + "</project>");
        java.nio.file.Files.writeString(pomXml.toPath(), pomContent);
        log.info("Added code quality plugins to pom.xml");
      }
    }
    templateService.renderCommonTemplates(config, dir);
    templateService.renderTemplate("java/gitignore.ftl", config, new File(dir, ".gitignore"));
    templateService.renderTemplate("java/gitattributes.ftl", config, new File(dir, ".gitattributes"));
    templateService.renderTemplate("java/editorconfig.ftl", config, new File(dir, ".editorconfig"));
    log.info("Java project '{}' scaffolded at {}", projectName, dir.getAbsolutePath());
  }

  @Override
  public void refactorProject(String projectName, File dir, Map<String, Object> config, String type) throws IOException {
    log.info("[Java] Refactoring project '{}' in '{}' with type '{}'", projectName, dir, type);
    // TODO: Add logic for refactoring Java project
  }
}
