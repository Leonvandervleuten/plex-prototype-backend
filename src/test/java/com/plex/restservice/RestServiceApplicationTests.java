package com.plex.restservice;

import com.plex.restservice.service.ProjectService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RestServiceApplicationTests {

  ProjectService projectService = new ProjectService();

  @Test
  void testCreateProjectlistForDeelnemer() throws JSONException {
    //Arrange
    JSONObject project1 = new JSONObject();
    project1.put("id", 158);
    JSONObject project2 = new JSONObject();
    project2.put("id", 156);

    JSONArray projectArray = new JSONArray();
    projectArray.put(project1);
    projectArray.put(project2);

    JSONObject mainObj = new JSONObject();
    mainObj.put("projectids", projectArray);
    mainObj.put("listName", "naamVanLijst");

    //Act
    JSONObject returnedProjectList = projectService.createProjectList(mainObj);

    //Assert
    Assertions.assertEquals(mainObj.get("listName"), returnedProjectList.get("listName"));
    Assertions.assertEquals(mainObj.length(), returnedProjectList.length());

  }

}
