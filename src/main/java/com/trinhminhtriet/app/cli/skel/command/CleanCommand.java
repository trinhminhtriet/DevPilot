package com.trinhminhtriet.app.cli.skel.command;

import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Component
@Command(name = "clean", description = "Clean build, cache, logs, temp files", mixinStandardHelpOptions = true)
public class CleanCommand implements Runnable {
    @Override
    public void run() {
        String[] targets = {
            "target", "build", "dist", "out", "bin", "node_modules", 
            ".mypy_cache", ".pytest_cache", "__pycache__", "*.log"
        };
        int removed = 0;
        for (String name : targets) {
            java.io.File f = new java.io.File(name);
            if (f.exists()) {
                deleteRecursively(f);
                System.out.println("Removed: " + f.getPath());
                removed++;
            }
        }
        if (removed == 0) {
            System.out.println("No build/cache/log/temp files found to clean.");
        }
    }

    private void deleteRecursively(java.io.File file) {
        if (file.isDirectory()) {
            java.io.File[] files = file.listFiles();
            if (files != null) {
                for (java.io.File child : files) {
                    deleteRecursively(child);
                }
            }
        }
        file.delete();
    }
}
