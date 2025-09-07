package com.trinhminhtriet.devpilot.command.git;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.File;

@Slf4j
@Component
@Command(name = "init", description = "Initialize a git repository in the target directory", mixinStandardHelpOptions = true)
public class GitInitCommand implements Runnable {
    @Option(names = {"--dir"}, description = "Target directory", defaultValue = ".")
    private File dir;

    @Override
    public void run() {
        try {
            ProcessBuilder pbInit = new ProcessBuilder("git", "init");
            pbInit.directory(dir);
            Process pInit = pbInit.start();
            pInit.waitFor();
            log.info("Initialized git repository in {}", dir.getAbsolutePath());
        } catch (Exception e) {
            log.error("Failed to initialize git repository: {}", e.getMessage());
        }
    }
}
