package com.plex.assembly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.plex")
@EnableJpaRepositories("com.plex.data")
@EntityScan(basePackages = "com.plex")
public class PLeXApplication {

  public static void main(String[] args) {
    SpringApplication.run(PLeXApplication.class, args);
  }

}