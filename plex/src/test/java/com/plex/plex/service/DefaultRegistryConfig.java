package com.plex.plex.service;

import com.plex.plex.domain.Project;
import com.plex.plex.domain.ProjectList;
import com.plex.plex.repository.DefaultProjectDataStrategy;
import com.plex.plex.repository.DefaultProjectListDataStrategy;
import com.plex.plex.repository.ProjectDataStategy;
import com.plex.plex.repository.ProjectListDataStretegy;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class DefaultRegistryConfig {

  @Bean
  public ProjectDataStategy projectDataStrategy() {
    return new DefaultProjectDataStrategy();
  }

  @Bean
  public ProjectListDataStretegy projectListDataStrategy() {
    ProjectListDataStretegy projectListDataStretegy = new DefaultProjectListDataStrategy();
    ProjectList projectList = new ProjectList();

    Set<Project> projectSet = new HashSet<>();
    Project project = new Project();
    project.setId(1L);
    project.setTitle("piet");
    projectSet.add(project);

    projectList.setProjects(projectSet);
    projectListDataStretegy.save(projectList);

    return projectListDataStretegy;
  }
}