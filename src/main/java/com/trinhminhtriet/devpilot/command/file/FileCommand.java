package com.trinhminhtriet.devpilot.command.file;

import com.trinhminhtriet.devpilot.command.vscode.AddVSCodeCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Slf4j
@Component
@Command(
    name = "file",
    description = "Add individual template files to your project",
    subcommands = {
        FileReadmeCommand.class,
        FileLicenseCommand.class,
        FileGitAttributesCommand.class,
        AddVSCodeCommand.class,
        FileEditorConfigCommand.class
    },
    mixinStandardHelpOptions = true
)
@RequiredArgsConstructor
public class FileCommand implements Runnable {

  @Override
  public void run() {
    log.info("Use subcommands: readme, license, editorconfig");
  }
}
