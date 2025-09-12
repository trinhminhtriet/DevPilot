package com.trinhminhtriet.devpilot.command;

import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine.Command;

@Slf4j
@Command(
    name = "ui",
    description = "Run UI",
    mixinStandardHelpOptions = true
)
public class UICommand implements Runnable {

  @Override
  public void run() {
    DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
    try (Terminal terminal = terminalFactory.createTerminal()) {
      Screen screen = new TerminalScreen(terminal);
      screen.startScreen();

      WindowBasedTextGUI textGUI = new MultiWindowTextGUI(screen);
      BasicWindow window = new BasicWindow("Dev Journal");

      Panel panel = new Panel();
      panel.setLayoutManager(new GridLayout(3));
      panel.addComponent(new Label("ID"));
      panel.addComponent(new Label("Content"));
      panel.addComponent(new Label("Status"));

      // Sample rows
      panel.addComponent(new Label("1"));
      panel.addComponent(new Label("Investigate NPE"));
      panel.addComponent(new Label("TODO"));

      panel.addComponent(new Label("2"));
      panel.addComponent(new Label("Refactor auth"));
      panel.addComponent(new Label("IN PROGRESS"));

      window.setComponent(panel);
      textGUI.addWindowAndWait(window);
    } catch (IOException e) {
      log.info(e.getMessage());
    }
  }
}
