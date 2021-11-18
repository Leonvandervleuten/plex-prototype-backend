package com.plex.restservice;

import com.plex.restservice.challenge.Challenge;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest
class RestServiceApplicationTests {

  MainService mainService = new MainService();

  @Test
  void testCreateProjectlistForDeelnemer() throws JSONException {
    //Arrange
    JSONObject project1 = new JSONObject();
    project1.put("ID", 158);
    JSONObject project2 = new JSONObject();
    project2.put("ID", 156);

    JSONArray projectArray = new JSONArray();
    projectArray.put(project1);
    projectArray.put(project2);

    JSONObject mainObj = new JSONObject();
    mainObj.put("ProjectIds", projectArray);
    mainObj.put("Name", "NaamVanLijst");

    System.out.println(mainObj);

    //Act
    mainService.createProjectList(mainObj);




    //Assert

  }

}
