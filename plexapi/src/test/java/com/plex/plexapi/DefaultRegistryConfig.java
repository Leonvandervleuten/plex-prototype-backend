package com.plex.plexapi;

import com.plex.plex.repository.DefaultProjectDataStrategy;
import com.plex.plex.repository.DefaultProjectListDataStrategy;
import com.plex.plex.repository.ProjectDataStategy;
import com.plex.plex.repository.ProjectListDataStretegy;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class DefaultRegistryConfig {

  @Bean
  public ProjectDataStategy projectDataStrategy() {
    return new DefaultProjectDataStrategy();
  }

  @Bean
  public ProjectListDataStretegy projectListDataStrategy() {
    return new DefaultProjectListDataStrategy();
  }
}