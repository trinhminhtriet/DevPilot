package com.trinhminhtriet.app.cli.skel.runner;

import com.trinhminhtriet.app.cli.skel.command.RootCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import picocli.CommandLine;
import picocli.spring.PicocliSpringFactory;

@Component
@RequiredArgsConstructor
public class PicocliRunner implements ApplicationRunner {

  private final RootCommand rootCommand;
  private final ApplicationContext applicationContext;


  @Override
  public void run(ApplicationArguments args) {
    CommandLine cmd = new CommandLine(rootCommand, new PicocliSpringFactory(applicationContext));
    cmd.execute(args.getSourceArgs());
  }
}
