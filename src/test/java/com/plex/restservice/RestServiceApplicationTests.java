package com.plex.restservice;

import com.plex.restservice.domain.Challenge;
import com.plex.restservice.dto.NewChallengeListDTO;
import com.plex.restservice.service.ProjectService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class RestServiceApplicationTests {

  ProjectService projectService = new ProjectService();

  @Test
  void testCreateProjectlistForDeelnemer() {
    //Arrange
    List<Long> idList = new ArrayList<>(2);
    idList.add(158L);
    idList.add(156L);

    //Act
    NewChallengeListDTO challengeList = new NewChallengeListDTO();
    challengeList.setName("projectList");
    challengeList.setChallengeIds(idList);
    projectService.createChallengeList(challengeList);
    List<Challenge> result = projectService.challengeListForStudent();


    //Assert
    Assertions.assertEquals(idList.size(), result.size());
  }

}
