package com.plex.plexapi.service;

import com.plex.data.domain.Project;
import com.plex.data.repository.ChallengeListRepository;
import com.plex.data.repository.ProjectRepository;
import com.plex.dexapi.service.ProjectService;
import com.plex.plexapi.domain.NewChallengeList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = {
    ChallengeService.class,
    ProjectRepository.class,
    ChallengeListRepository.class,
    ProjectService.class
})
class ProjectServiceTest {

  @Autowired
  private ChallengeService challengeService;

  @Test
  void testCreateProjectlistForDeelnemer() {
    //Arrange
    List<Long> idList = new ArrayList<>(2);
    idList.add(158L);
    idList.add(156L);

    //Act
    NewChallengeList challengeList = new NewChallengeList();
    challengeList.setName("projectList");
    challengeList.setChallengeIds(idList);
    challengeService.createChallengeList(challengeList);
    List<Project> result = challengeService.challengeListForStudent();

    //Assert
    Assertions.assertEquals(idList.size(), result.size());
  }

}
