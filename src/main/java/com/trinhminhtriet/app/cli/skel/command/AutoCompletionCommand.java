package com.trinhminhtriet.app.cli.skel.command;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import picocli.AutoComplete;
import picocli.CommandLine.Command;

@Slf4j
@Component
@Command(
    name = "autocomplete",
    description = "Generate shell auto-completion script"
)
public class AutoCompletionCommand implements Runnable {

  @Override
  public void run() {
    try {
      AutoComplete.main(new String[]{
          "-n", "skel",
          "-o", "skel_completion.sh",
          "com.trinhminhtriet.app.cli.skel.command.standalone.VersionCommand"
      });
      log.info("Generated autocomplete script: skel_completion.sh");
    } catch (Exception e) {
      log.info(e.getMessage());
      e.printStackTrace();
    }
  }
}
