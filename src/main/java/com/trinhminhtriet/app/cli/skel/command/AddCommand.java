package com.trinhminhtriet.app.cli.skel.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Slf4j
@Component
@Command(
    name = "add",
    description = "Add individual template files to your project",
    subcommands = {
        AddReadmeCommand.class,
        AddLicenseCommand.class,
        AddEditorConfigCommand.class
    },
    mixinStandardHelpOptions = true
)
@RequiredArgsConstructor
public class AddCommand implements Runnable {

  @Override
  public void run() {
    log.info("Use subcommands: readme, license, editorconfig");
  }
}
