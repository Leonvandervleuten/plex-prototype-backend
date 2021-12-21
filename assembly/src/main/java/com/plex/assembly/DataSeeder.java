package com.plex.assembly;

import com.plex.plex.repository.ProjectDataStategy;
import com.plex.plex.service.ExternalProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

  private final ProjectDataStategy projectDataStategy;
  private final ExternalProjectService externalProjectService;

  @Autowired
  public DataSeeder(ProjectDataStategy projectDataStategy, ExternalProjectService externalProjectService) {
    this.projectDataStategy = projectDataStategy;
    this.externalProjectService = externalProjectService;
  }

  @Override
  public void run(String... args) {
    projectDataStategy.saveAll(externalProjectService.getProjects());
  }
}

