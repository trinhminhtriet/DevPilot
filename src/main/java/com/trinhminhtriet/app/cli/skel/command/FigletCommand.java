package com.trinhminhtriet.app.cli.skel.command;

import com.github.lalyos.jfiglet.FigletFont;
import java.io.IOException;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Component
@Command(
    name = "figlet",
    description = "Prints Figlet text for the given input",
    mixinStandardHelpOptions = true
)
public class FigletCommand implements Runnable {

  @Parameters(index = "0", description = "Text to convert to Figlet")
  private String text;

  @Override
  public void run() {
    String figletText = null;
    try {
      figletText = FigletFont.convertOneLine(text);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    System.out.println(figletText);
  }
}
