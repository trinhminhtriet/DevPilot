package com.trinhminhtriet.devpilot.service;

import java.util.Map;

public interface ConfigService {

  /**
   * Load config from ~/.devpilot/config.yml
   *
   * @return Map of config values (authorName, authorEmail, license, ...)
   */
  Map<String, Object> loadConfig();

  /**
   * Save config to ~/.devpilot/config.yml
   */
  void saveConfig(Map<String, Object> config);

  /**
   * Get flatten value by key (e.g. "user.name") from config
   */
  String getValue(String key);

  /**
   * Get the absolute path to the config file (e.g. ~/.devpilot/config.yml)
   */
  String getConfigFilePath();

}
