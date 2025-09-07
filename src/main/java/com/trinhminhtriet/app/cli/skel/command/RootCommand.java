package com.trinhminhtriet.app.cli.skel.command;

import com.trinhminhtriet.app.cli.skel.command.common.ListCommand;
import com.trinhminhtriet.app.cli.skel.command.standalone.FigletCommand;
import com.trinhminhtriet.app.cli.skel.command.standalone.VersionCommand;
import com.trinhminhtriet.app.cli.skel.command.file.AddCommand;
import com.trinhminhtriet.app.cli.skel.command.git.GitCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Slf4j
@Component
@Command(
    name = "skel",
    mixinStandardHelpOptions = true,
    version = "skel 1.0.0",
    description = "Project scaffolding CLI generator",
    subcommands = {
        StrapiCommand.class,
        GitCommand.class,
        AddCommand.class,
        RefactorCommand.class,
        InitCommand.class,
        ListCommand.class,
        FigletCommand.class,
        VersionCommand.class,
        AutoCompletionCommand.class
    }
)
public class RootCommand implements Runnable {

  @Override
  public void run() {
    log.info("Use --help to see available commands.");
  }
}
