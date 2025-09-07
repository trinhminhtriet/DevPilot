package com.trinhminhtriet.devpilot.command;

import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Component
@Command(name = "serve", description = "Run dev server (web, API, docs, etc)", mixinStandardHelpOptions = true)
public class ServeCommand implements Runnable {
    @Override
    public void run() {
        System.out.println("Serve: Running dev server...");
    }
}
