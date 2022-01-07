package com.plex.plex.service;

import com.plex.plex.domain.Project;
import com.plex.plex.domain.ProjectList;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.HashSet;
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

//  @BeforeEach
//  public void init(){
//    ProjectList projectList = new ProjectList();
//    projectList.setId(1L);
//    projectList.setName("pietje");
//
//    Project project = new Project();
//    project.setId(1L);
//    project.setTitle("testje");
//
//    Set<Project> projectSet = new HashSet<>();
//    projectSet.add(project);
//    projectList.setProjects(projectSet);
//
//    projectService.createChallengeList(projectList);
//
//  }

  @Test
  void testCreateProjectlistForDeelnemer() {
    //Arrange

    //Act
    List<Project> result = projectService.challengeListForStudent();

    //Assert
    Project blabla = result.get(0);
  }
}