package com.trinhminhtriet.devpilot.command.standalone;

import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Component
@Command(name = "doc", description = "Generate project documentation from code/comments", mixinStandardHelpOptions = true)
public class DocCommand implements Runnable {
    @Override
    public void run() {
        System.out.println("Doc: Generating documentation...");
    }
}
