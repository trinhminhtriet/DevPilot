package com.trinhminhtriet.devpilot;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class DevPilotApplication {

  public static void main(String[] args) {
    new SpringApplicationBuilder(DevPilotApplication.class)
        .logStartupInfo(false) // reduce log noise
        .run(args);
  }

}
