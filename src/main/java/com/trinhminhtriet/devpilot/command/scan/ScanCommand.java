package com.trinhminhtriet.devpilot.command.scan;

import picocli.CommandLine;

@CommandLine.Command(
    name = "scan",
    description = "Run code scans (secret, i18n, dep, metrics, ...)",
    subcommands = {ScanSecretCommand.class}
)
public class ScanCommand implements Runnable {
    @Override
    public void run() {
        System.out.println("Please specify a scan type. Example: devpilot scan secret");
    }
}
