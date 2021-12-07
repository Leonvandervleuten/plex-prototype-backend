package com.plex.data.repository;

import com.plex.data.domain.Challenge;
import com.plex.data.domain.ChallengeList;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Repository
public class ChallengeListRepository {

  // TODO Use ORM instead of class variables
  private static Long CURRENT_ID = 1L;
  private final List<ChallengeList> challengeLists = new ArrayList<>();

  public ChallengeList getMostRecentChallengeList() {
    return challengeLists
        .stream()
        .max(Comparator.comparing(ChallengeList::getId))
        .orElse(null);
  }

  public void addChallengeList(String name, List<Challenge> challengeIds) {
    ChallengeList newChallengeList = new ChallengeList(CURRENT_ID++, name, challengeIds);
    challengeLists.add(newChallengeList);
  }
}