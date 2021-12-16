package com.plex.plexapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.plex")
public class RestServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(RestServiceApplication.class, args);
  }

}
