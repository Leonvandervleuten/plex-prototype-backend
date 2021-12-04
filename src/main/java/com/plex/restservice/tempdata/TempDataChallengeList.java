package com.plex.restservice.tempdata;

import com.plex.restservice.domain.Challenge;

import java.util.Collections;
import java.util.List;

public class TempDataChallengeList {

  public String name = "No list created";
  public List<Challenge> tempChallengeList = Collections.emptyList();

  public TempDataChallengeList() {
  }

  public List<Challenge> getTempChallengeList() {
    return tempChallengeList;
  }

  public void setMockChallengeList(String name, List<Challenge> mockChallengeList) {
    this.name = name;
    this.tempChallengeList = mockChallengeList;
  }
}
