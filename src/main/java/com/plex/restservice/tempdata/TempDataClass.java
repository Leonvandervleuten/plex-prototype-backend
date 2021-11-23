package com.plex.restservice.tempdata;

import com.plex.restservice.domain.ProjectList;

import java.util.Arrays;

public abstract class TempDataClass {
  public static ProjectList tempChallengeList(Long id) {
    if (id == 1) {
      return new ProjectList(1, Arrays.asList(158L, 140L, 146L, 151L, 148L, 145L, 143L));
    }
    else if (id == 2) {
      return new ProjectList(2, Arrays.asList(140L, 119L, 149L, 150L, 147L, 144L));
    }
    return new ProjectList(0, Arrays.asList());
  }

}
