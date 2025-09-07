package com.trinhminhtriet.devpilot.service;

import java.io.File;
import java.util.Map;

public interface TomlService {

  /**
   * Ghi dữ liệu ra file TOML
   *
   * @param tomlFile file TOML cần ghi
   * @param tomlData dữ liệu Map
   */
  void writeToml(File tomlFile, Map<String, Object> tomlData);

  /**
   * Phân tích file TOML và trả về dữ liệu dưới dạng Map
   *
   * @param tomlFile file TOML cần phân tích
   * @return Map dữ liệu
   */
  Map<String, Object> readToml(File tomlFile);
}
