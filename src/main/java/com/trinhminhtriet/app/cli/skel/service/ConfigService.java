package com.trinhminhtriet.app.cli.skel.service;

import java.util.Optional;
import java.util.Map;

public interface ConfigService {
    /**
     * Load config from ~/.skel/config.yml
     * @return Map of config values (authorName, authorEmail, license, ...)
     */
    Map<String, String> loadConfig();

    /**
     * Get config value by key
     */
    Optional<String> getConfigValue(String key);

    /**
     * Save config to ~/.skel/config.yml
     */
    void saveConfig(Map<String, String> config);
}
