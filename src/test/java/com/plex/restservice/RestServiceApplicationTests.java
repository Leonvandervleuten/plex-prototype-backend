package com.plex.restservice;

import com.plex.restservice.challenge.Challenge;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import springfox.documentation.spring.web.json.Json;

import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.get;

@SpringBootTest
class RestServiceApplicationTests {

  MainService mainService = new MainService();

  @Test
  void testCreateProjectlistForDeelnemer() throws JSONException {
    //Arrange
    JSONObject project1 = new JSONObject();
    project1.put("ID", 1);
    JSONObject project2 = new JSONObject();
    project2.put("ID", 2);

    JSONArray projectArray = new JSONArray();
    projectArray.put(project1);
    projectArray.put(project2);

    JSONObject mainObj = new JSONObject();
    mainObj.put("Projectnaam", projectArray);

    List<Challenge> projects = mainService.getChallenges();

    //Act
    mainService.createProjectList();




    //Assert

  }

}
