package com.trinhminhtriet.app.cli.skel.service;

public interface ProjectGeneratorService {

  /**
   * Generate project skeleton for given language
   *
   * @param language    programming language (golang, rust, python, typescript)
   * @param projectName project folder name
   */
  void generate(String language, String projectName);
}
