package com.plex.data.repository;

import com.plex.data.domain.Challenge;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class ChallengeRepository {

  List<Challenge> challenges = Collections.emptyList();

  public List<Challenge> getChallenges() {
    return challenges;
  }

  public Challenge getChallenge(Long id) {
    return getChallenges().stream()
        .filter(challenge -> challenge.getId().equals(id))
        .findFirst().orElse(null);
  }

  public void setChallenges(List<Challenge> challenges) {
    this.challenges = challenges;
  }
}