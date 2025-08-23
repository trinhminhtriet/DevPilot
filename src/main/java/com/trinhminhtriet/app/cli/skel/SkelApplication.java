package com.trinhminhtriet.app.cli.skel;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SkelApplication {

  public static void main(String[] args) {
    new SpringApplicationBuilder(SkelApplication.class)
        .logStartupInfo(false) // giảm log rác
        .run(args);
  }

}
