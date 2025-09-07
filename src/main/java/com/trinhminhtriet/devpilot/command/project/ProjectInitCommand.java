package com.trinhminhtriet.devpilot.command.project;

import com.trinhminhtriet.devpilot.service.ConfigService;
import com.trinhminhtriet.devpilot.service.ProjectScaffoldService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Command(name = "init", description = "Initialize a new project", mixinStandardHelpOptions = true)
public class ProjectInitCommand implements Runnable {

    @Option(names = "--lang", description = "Project language (rust, go, python, typescript)", required = true)
    private String lang;

    @Option(names = "--name", description = "Project name", required = true)
    private String name;

    private final Map<String, ProjectScaffoldService> scaffoldServices;
    private final ConfigService configService;

    @Override
    public void run() {
        ProjectScaffoldService service = scaffoldServices.get(lang.toLowerCase() + "ProjectScaffoldServiceImpl");
        if (service == null) {
            System.err.printf("Unsupported language: %s%n", lang);
            return;
        }
        System.out.printf("[%s] Initializing project: %s%n", lang, name);
        try {
            Map<String, Object> objectMapping = new HashMap<>(configService.loadConfig());
            objectMapping.put("projectName", name);

            File dir = new File(name);
            service.scaffoldProject(name, dir, objectMapping);
        } catch (Exception e) {
            System.err.println("Error initializing " + lang + " project: " + e.getMessage());
        }
    }
}
