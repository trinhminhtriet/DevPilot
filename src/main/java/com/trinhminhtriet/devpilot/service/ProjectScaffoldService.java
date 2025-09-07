package com.trinhminhtriet.devpilot.service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public interface ProjectScaffoldService {
    void scaffoldProject(String projectName, File dir, Map<String, Object> config) throws IOException;
}
