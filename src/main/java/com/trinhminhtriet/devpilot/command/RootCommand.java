package com.trinhminhtriet.devpilot.command;

import com.trinhminhtriet.devpilot.command.common.ConfigCommand;
import com.trinhminhtriet.devpilot.command.common.InfoCommand;
import com.trinhminhtriet.devpilot.command.common.ListCommand;
import com.trinhminhtriet.devpilot.command.common.VersionCommand;
import com.trinhminhtriet.devpilot.command.file.EnvCommand;
import com.trinhminhtriet.devpilot.command.file.FileCommand;
import com.trinhminhtriet.devpilot.command.git.GitCommand;
import com.trinhminhtriet.devpilot.command.mfa.MfaCommand;
import com.trinhminhtriet.devpilot.command.project.ProjectCommand;
import com.trinhminhtriet.devpilot.command.scan.ScanCommand;
import com.trinhminhtriet.devpilot.command.standalone.FigletCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Slf4j
@Component
@Command(
    name = "devpilot",
    mixinStandardHelpOptions = true,
    version = "DevPilot 1.0.0",
    description = "\uD83D\uDE80 All-in-one CLI toolkit for developers: scaffold, manage, automate, and boost productivity across languages!",
    subcommands = {
        UICommand.class,
        StrapiCommand.class,
        GitCommand.class,
        FileCommand.class,
        ListCommand.class,
        FigletCommand.class,
        VersionCommand.class,
        InfoCommand.class,
        ConfigCommand.class,
        ScanCommand.class,
        EnvCommand.class,
        ProjectCommand.class,
        AutoCompletionCommand.class,
        DnsRecordCommand.class,
        MfaCommand.class
    }
)
public class RootCommand implements Runnable {

  @Override
  public void run() {
    log.info("Use --help to see available commands.");
  }
}
