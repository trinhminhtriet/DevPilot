package com.trinhminhtriet.app.cli.skel.command.standalone;

import com.trinhminhtriet.app.cli.skel.service.ConfigService;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Slf4j
@Component
@RequiredArgsConstructor
@Command(
    name = "info",
    description = "Show project information (name, version, author, license, etc)",
    mixinStandardHelpOptions = true
)
public class InfoCommand implements Runnable {

    @Option(names = {"--dir"}, description = "Project directory", required = false)
    private File dir = new File(".");

    private final ConfigService configService;

    @Override
    public void run() {
        Map<String, Object> config = new HashMap<>(configService.loadConfig());
        String projectName = config.getOrDefault("projectName", dir.getName()).toString();
        String author = "";

        String license = config.getOrDefault("license", "").toString();
        String version = config.getOrDefault("version", "0.1.0").toString();
        System.out.println("Project: " + projectName);
        System.out.println("Version: " + version);
        System.out.println("Author: " + author);
        System.out.println("License: " + license);
        System.out.println("Directory: " + dir.getAbsolutePath());
    }
}
