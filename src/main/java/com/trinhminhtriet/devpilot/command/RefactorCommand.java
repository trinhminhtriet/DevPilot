package com.trinhminhtriet.devpilot.command;

import com.trinhminhtriet.devpilot.command.rust.RefactorRustCommand;
import picocli.CommandLine.Command;
import org.springframework.stereotype.Component;

@Component
@Command(
    name = "refactor",
    description = "Refactor project source code",
    subcommands = {RefactorRustCommand.class},
    mixinStandardHelpOptions = true
)
public class RefactorCommand implements Runnable {
    @Override
    public void run() {
        // Show help or list available refactor subcommands
    }
}
