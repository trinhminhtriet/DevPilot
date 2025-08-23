package com.trinhminhtriet.app.cli.skel.runner;

import com.trinhminhtriet.app.cli.skel.command.RootCommand;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

@Component
public class PicocliRunner implements ApplicationRunner {

  private final RootCommand rootCommand;

  public PicocliRunner(RootCommand rootCommand) {
    this.rootCommand = rootCommand;
  }

  @Override
  public void run(ApplicationArguments args) {
    new CommandLine(rootCommand).execute(args.getSourceArgs());
  }
}
