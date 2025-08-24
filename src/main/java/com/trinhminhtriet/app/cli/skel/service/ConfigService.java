package com.trinhminhtriet.app.cli.skel.service;

import java.util.Map;
import java.util.Optional;

public interface ConfigService {

  /**
   * Load config from ~/.skel/config.yml
   *
   * @return Map of config values (authorName, authorEmail, license, ...)
   */
  Map<String, Object> loadConfig();

  /**
   * Save config to ~/.skel/config.yml
   */
  void saveConfig(Map<String, Object> config);

    /**
   * Get flatten value by key (e.g. "user.name") from config
   */
  String getValue(String key);
}
