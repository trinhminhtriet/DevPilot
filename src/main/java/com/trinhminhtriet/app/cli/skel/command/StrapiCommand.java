package com.trinhminhtriet.app.cli.skel.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;


@Slf4j
@Component
@Command(
    name = "strapi",
    description = "Initialize project scaffolding",
    subcommands = {
        StrapiEntityCommand.class
    },
    mixinStandardHelpOptions = true
)
@RequiredArgsConstructor
public class StrapiCommand implements Runnable {

  @Override
  public void run() {
    log.info("Vui rồi");
  }
}
