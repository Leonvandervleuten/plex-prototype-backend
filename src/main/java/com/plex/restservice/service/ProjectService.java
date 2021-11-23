package com.plex.restservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.plex.restservice.tempdata.TempDataProjectList;
import com.plex.restservice.domain.Category;
import com.plex.restservice.domain.Project;
import com.plex.restservice.domain.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {

  TempDataProjectList tempDataProjectList = new TempDataProjectList();

  public Project getChallengeById(long id) {
    Project project = null;
    String POSTS_API_URL = "https://api.dex.software/api/Project/" + id;
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .GET()
        .header("accept", "application/json")
        .uri(URI.create(POSTS_API_URL))
        .build();
    try {
      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
      project = createChallengeFromJson(response.body());
    }
    catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
    return project;
  }

  public List<Project> getChallenges() {
    List<Project> projects = null;
    String POSTS_API_URL = "https://api.dex.software/api/Project";
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .GET()
        .header("accept", "application/json")
        .uri(URI.create(POSTS_API_URL))
        .build();
    try {
      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
      projects = createChallengeFromJsonArray(response.body());
    }
    catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
    return projects;
  }

  //Used for DeX API call /project/id <-- get single project | Json Data is different from /project
  private Project createChallengeFromJson(String json) throws JsonProcessingException {
    JsonNode challengeNode = new ObjectMapper().readTree(json);

    //basic values
    Long id = challengeNode.get("id").asLong();
    Long userId = challengeNode.get("userId").asLong();
    String name = challengeNode.get("name").textValue();
    String description = challengeNode.get("description").textValue();
    String shortDescription = challengeNode.get("shortDescription").textValue();
    String uri = challengeNode.get("uri").textValue();
    boolean institutePrivate = challengeNode.get("institutePrivate").booleanValue();

    //create object without nested
    Project project = new Project(id, userId, name, description, shortDescription, uri, institutePrivate);

    //add nested user
    project.setUser(
        new User(challengeNode.get("user").get("id").asLong(), challengeNode.get("user").get("name").textValue(),
            challengeNode.get("user").get("email").textValue()));

    //add array of categories
    JsonNode categoriesNode = challengeNode.get("categories");
    if (categoriesNode.isArray()) {
      List<Category> categoriesArray = new ArrayList<>();
      for (JsonNode category : categoriesNode) {
        categoriesArray.add(new Category(category.get("id").asLong(), category.get("name").textValue()));
      }
      project.setCategories(categoriesArray);
    }
    //TODO Add: collaborators array, linkedInsitutions array, Dates, Project icon object, call to action array, likes array, imamge array
    return project;
  }

  //Used for DeX API call /project <-- gets all projects | Json Data is different from /project/id
  private List<Project> createChallengeFromJsonArray(String json) throws JsonProcessingException {
    List<Project> projects = new ArrayList<>();
    JsonNode rootArray = new ObjectMapper().readTree(json).get("results");

    //basic values
    for (JsonNode root : rootArray) {
      Long id = root.get("id").asLong();
      String name = root.get("name").textValue();
      String shortDescription = root.get("shortDescription").textValue();

      //create object without nested
      Project project = new Project(id, name, shortDescription);

      //add nested user
      project.setUser(new User(root.get("user").get("id").asLong(), root.get("user").get("name").textValue(),
          root.get("user").get("email").textValue()));

      //add array of categories
      JsonNode categoriesNode = root.get("categories");
      if (categoriesNode.isArray()) {
        List<Category> categoriesArray = new ArrayList<>();
        for (JsonNode category : categoriesNode) {
          categoriesArray.add(new Category(category.get("id").asLong(), category.get("name").textValue()));
        }
        project.setCategories(categoriesArray);
      }

      //TODO Add: collaborators array, linkedInsitutions array, Dates, Project icon object, call to action array, likes array, imamge array.
      projects.add(project);
    }
    return projects;
  }

  public List<Project> selectProjectsBasedOnID(JSONObject requestedList) throws JSONException {

    List<Integer> idList = makeListOfIDs(requestedList);
    List<Project> fullProjectList = getChallenges();
    List<Project> selectedProjects = new ArrayList<>();

    for (int idFromIdList : idList) {
      for (Project project : fullProjectList) {
        int idFromFullProjectList = Math.toIntExact(project.id);
        if (idFromIdList == idFromFullProjectList) {
          selectedProjects.add(project);
        }
      }
    }
    return selectedProjects;
  }

  public List<Integer> makeListOfIDs(JSONObject inputForListId) throws JSONException {

    JSONArray projectIds = inputForListId.getJSONArray("projectids");
    List<Integer> idList = new ArrayList<>();
    for (int i = 0; i < projectIds.length(); i++) {
      JSONObject jsn = projectIds.getJSONObject(i);
      idList.add(jsn.getInt("id"));
    }
    return idList;

  }

  public JSONObject createProjectList(JSONObject inputJsonObject) throws JSONException {

    List<Project> projectList = selectProjectsBasedOnID(inputJsonObject);

    String name = (String) inputJsonObject.get("listName");
    JSONArray listArray = new JSONArray();
    for (Project project : projectList) {
      listArray.put(project);
    }

    JSONObject mainObjList = new JSONObject();
    mainObjList.put("listName", name);
    mainObjList.put("projects", listArray);

    tempDataProjectList.setMockProjectList(name, projectList);

    return mainObjList;
  }

  public List<Project> projectListForStudent() {
    return tempDataProjectList.getTempProjectList();
  }
}