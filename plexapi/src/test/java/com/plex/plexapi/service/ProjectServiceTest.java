package com.plex.plexapi.service;

import com.plex.plex.domain.Project;
import com.plex.plex.domain.ProjectList;
import com.plex.plex.repository.ProjectDataStategy;
import com.plex.plex.repository.ProjectListDataStretegy;
import com.plex.plex.service.ProjectService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootTest(classes = {
    ProjectService.class,
    ProjectDataStategy.class,
    ProjectListDataStretegy.class
})
class ProjectServiceTest {

  @Autowired
  private ProjectService challengeService;

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
    challengeService.createChallengeList(projectList);
    List<Project> result = challengeService.challengeListForStudent();

    //Assert
    Assertions.assertEquals(idList.size(), result.size());
  }

}
