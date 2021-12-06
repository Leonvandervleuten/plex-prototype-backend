package com.plex.data.domain;

import java.util.List;

public class ChallengeList {
  private Long id;
  private String name;
  private List<Challenge> challenges;
  //TODO Add: End + start date?
  //TODO add: Students

  public ChallengeList(long id, String name, List<Challenge> challenges) {
    // TODO Use ORM to determine id
    this.id = id;
    this.name = name;
    this.challenges = challenges;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Challenge> getChallenges() {
    return challenges;
  }

  public void setChallengeIds(List<Challenge> challenges) {
    this.challenges = challenges;
  }
}