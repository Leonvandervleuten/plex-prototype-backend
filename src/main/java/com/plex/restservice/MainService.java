package com.plex.restservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.plex.restservice.challenge.Challenge;
import com.plex.restservice.challenge.User;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class MainService {

    public List<Challenge> getChallengeList(Long id){
        List<Challenge> challengeList = new ArrayList<>();
        List<Long> challengeIds = TempDataClass.tempChallengeList(id).getChallengeIds();
        for (Long item : challengeIds) {
            challengeList.add(getChallengeById(item));
        }
        return challengeList;
    }

    public Challenge getChallengeById(long id){
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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return challenge;
    }

    public List<Challenge> getChallenges(){
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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return challenges;
    }

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
        Challenge challenge = new Challenge(id, userId, name, description, shortDescription, uri, institutePrivate);

        //add nested
        challenge.setUser(new User(challengeNode.get("user").get("id").asLong(), challengeNode.get("user").get("name").textValue(), challengeNode.get("user").get("email").textValue()));
        //TODO Add: collaborators array, linkedInsitutions array, Dates, Project icon object, call to action array, likes array, imamge array, categories array?
        return challenge;
    }
    private List<Challenge> createChallengeFromJsonArray(String json) throws JsonProcessingException {
        List<Challenge> challenges= new ArrayList<>();
        JsonNode rootArray = new ObjectMapper().readTree(json).get("results");

        //basic values
        for(JsonNode root : rootArray){
            Long id = root.get("id").asLong();
            String name = root.get("name").textValue();
            String shortDescription = root.get("shortDescription").textValue();

            //create object without nested
            Challenge challenge = new Challenge(id, name, shortDescription);

            //add nested
            challenge.setUser(new User(root.get("user").get("id").asLong(), root.get("user").get("name").textValue(), root.get("user").get("email").textValue()));
            //TODO Add: collaborators array, linkedInsitutions array, Dates, Project icon object, call to action array, likes array, imamge array, categories array.
            challenges.add(challenge);
        }
        return challenges;
    }
}
