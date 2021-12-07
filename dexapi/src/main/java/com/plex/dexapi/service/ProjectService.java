package com.plex.dexapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.plex.data.domain.Category;
import com.plex.data.domain.Challenge;
import com.plex.dexapi.domain.User;
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

  public Challenge getChallengeById(long id) {
    Challenge challenge = null;
    String POSTS_API_URL = "https://api.dex.software/api/Project/" + id;
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .GET()
        .header("accept", "application/json")
        .uri(URI.create(POSTS_API_URL))
        .build();
    try {
      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
      challenge = createChallengeFromJson(response.body());
    }
    catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
    return challenge;
  }

  public List<Challenge> getChallenges() {
    List<Challenge> challenges = null;
    String POSTS_API_URL = "https://api.dex.software/api/Project";
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .GET()
        .header("accept", "application/json")
        .uri(URI.create(POSTS_API_URL))
        .build();
    try {
      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
      challenges = createChallengeFromJsonArray(response.body());
    }
    catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
    return challenges;
  }

  //Used for DeX API call /project/id <-- get single project | Json Data is different from /project
  private Challenge createChallengeFromJson(String json) throws JsonProcessingException {
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
    Challenge challenge = new Challenge();
    challenge.setId(id);
    challenge.setTitle(name);
    challenge.setShortDescription(shortDescription);

    //add nested user
    User user = new User(challengeNode.get("user").get("id").asLong(), challengeNode.get("user").get("name").textValue(),
            challengeNode.get("user").get("email").textValue());
    challenge.setUploader(user.getName());

    //add array of categories
    JsonNode categoriesNode = challengeNode.get("categories");
    if (categoriesNode.isArray()) {
      List<Category> categoriesArray = new ArrayList<>();
      for (JsonNode categoryJson : categoriesNode) {
        Category category = new Category();
        category.setId(categoryJson.get("id").asLong());
        category.setName(categoryJson.get("name").textValue());
        categoriesArray.add(category);
      }
      challenge.setCategories(categoriesArray);
    }
    //TODO Add: collaborators array, linkedInsitutions array, Dates, Project icon object, call to action array, likes array, imamge array
    return challenge;
  }

  //Used for DeX API call /project <-- gets all projects | Json Data is different from /project/id
  private List<Challenge> createChallengeFromJsonArray(String json) throws JsonProcessingException {
    List<Challenge> challenges = new ArrayList<>();
    JsonNode rootArray = new ObjectMapper().readTree(json).get("results");

    //basic values
    for (JsonNode root : rootArray) {
      Long id = root.get("id").asLong();
      String name = root.get("name").textValue();
      String shortDescription = root.get("shortDescription").textValue();

      //create object without nested
      Challenge challenge = new Challenge();
      challenge.setId(id);
      challenge.setTitle(name);
      challenge.setShortDescription(shortDescription);

      //add nested user
      User user = new User(root.get("user").get("id").asLong(), root.get("user").get("name").textValue(),
          root.get("user").get("email").textValue());
      challenge.setUploader(user.getName());

      //add array of categories
      JsonNode categoriesNode = root.get("categories");
      if (categoriesNode.isArray()) {
        List<Category> categoriesArray = new ArrayList<>();
        for (JsonNode categoryJson : categoriesNode) {
          Category category = new Category();
          category.setId(categoryJson.get("id").asLong());
          category.setName(categoryJson.get("name").textValue());
          categoriesArray.add(category);
        }
        challenge.setCategories(categoriesArray);
      }

      //TODO Add: collaborators array, linkedInsitutions array, Dates, Project icon object, call to action array, likes array, imamge array.
      challenges.add(challenge);
    }
    return challenges;
  }
}