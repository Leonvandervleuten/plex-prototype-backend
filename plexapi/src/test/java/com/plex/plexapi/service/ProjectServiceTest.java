package com.plex.plexapi.service;

import com.plex.plex.domain.Project;
import com.plex.plex.domain.ProjectList;
import com.plex.plex.service.ProjectService;
import com.plex.plexapi.DefaultRegistryConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootTest(classes = {
    ProjectService.class
})
@Import(DefaultRegistryConfig.class)
class ProjectServiceTest {

  @Autowired
  private ProjectService projectService;

  @Test
  void testCreateProjectlistForDeelnemer() {
    //Arrange
    List<Long> idList = new ArrayList<>(2);
    idList.add(158L);
    idList.add(156L);
    Set<Project> projects = idList.stream().map(id -> {
      Project project = new Project();
      project.setId(id);
      return project;
    }).collect(Collectors.toSet());
    ProjectList projectList = new ProjectList();
    projectList.setName("projectList");
    projectList.setProjects(projects);

    //Act
    projectService.createChallengeList(projectList);
    List<Project> result = projectService.challengeListForStudent();

    //Assert
    Assertions.assertEquals(idList.size(), result.size());
  }
}