package com.trinhminhtriet.app.cli.skel.command;

import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Component
@Command(name = "format", description = "Format project code", mixinStandardHelpOptions = true)
public class FormatCommand implements Runnable {
    @Override
    public void run() {
        System.out.println("Format: Formatting code...");
    }
}
