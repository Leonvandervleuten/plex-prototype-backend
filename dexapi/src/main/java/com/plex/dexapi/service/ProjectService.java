package com.plex.dexapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.plex.data.domain.Category;
import com.plex.data.domain.Project;
import com.plex.dexapi.domain.User;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProjectService {

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
    Project project = new Project();
    project.setId(id);
    project.setTitle(name);
    project.setShortDescription(shortDescription);

    //add nested user
    User user = new User(challengeNode.get("user").get("id").asLong(), challengeNode.get("user").get("name").textValue(),
            challengeNode.get("user").get("email").textValue());
    project.setUploader(user.getName());

    //add array of categories
    JsonNode categoriesNode = challengeNode.get("categories");
    if (categoriesNode.isArray()) {
      Set<Category> categoriesArray = new HashSet<>();
      for (JsonNode categoryJson : categoriesNode) {
        Category category = new Category();
        category.setId(categoryJson.get("id").asLong());
        category.setName(categoryJson.get("name").textValue());
        categoriesArray.add(category);
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
      Project project = new Project();
      project.setId(id);
      project.setTitle(name);
      project.setShortDescription(shortDescription);

      //add nested user
      User user = new User(root.get("user").get("id").asLong(), root.get("user").get("name").textValue(),
          root.get("user").get("email").textValue());
      project.setUploader(user.getName());

      //add array of categories
      JsonNode categoriesNode = root.get("categories");
      if (categoriesNode.isArray()) {
        Set<Category> categoriesArray = new HashSet<>();
        for (JsonNode categoryJson : categoriesNode) {
          Category category = new Category();
          category.setId(categoryJson.get("id").asLong());
          category.setName(categoryJson.get("name").textValue());
          categoriesArray.add(category);
        }
        project.setCategories(categoriesArray);
      }

      //TODO Add: collaborators array, linkedInsitutions array, Dates, Project icon object, call to action array, likes array, imamge array.
      projects.add(project);
    }
    return projects;
  }
}