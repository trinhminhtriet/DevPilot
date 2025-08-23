package com.trinhminhtriet.app.cli.skel.service.impl;

import com.trinhminhtriet.app.cli.skel.service.GeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GeneratorServiceImpl implements GeneratorService {

  @Override
  public void generate(String language, String projectName) {
    log.info("Starting project generation...");
    log.debug("Input params -> language={}, projectName={}", language, projectName);

    try {
      // TODO: real Freemarker logic
      log.info("Generating skeleton for [{}] in [{}]", projectName, language);
      System.out.println("✅ Skeleton generated for project " + projectName + " in language " + language);

    } catch (Exception e) {
      log.error("❌ Failed to generate skeleton: {}", e.getMessage(), e);
    }

    log.info("Project generation finished.");
  }
}

