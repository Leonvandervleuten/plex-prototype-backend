package com.plex.restservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class NewChallengeListDTO {

  @JsonProperty
  private String name;

  @JsonProperty
  private List<Long> challengeIds;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Long> getChallengeIds() {
    return challengeIds;
  }

  public void setChallengeIds(List<Long> challengeIds) {
    this.challengeIds = challengeIds;
  }
}