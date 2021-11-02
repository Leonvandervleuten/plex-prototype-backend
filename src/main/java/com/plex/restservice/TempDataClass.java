package com.plex.restservice;

import com.plex.restservice.challenge.ChallengeList;

import java.util.Arrays;

public abstract class TempDataClass {
    public static ChallengeList tempChallengeList(Long id){
        if (id== 1){
            return new ChallengeList(1, Arrays.asList(158L,140L,146L,151L,148L, 145L,143L));
        } else if (id == 2){
            return new ChallengeList(2, Arrays.asList(140L,119L,149L,150L, 147L, 144L));
        }
        return new ChallengeList(0, Arrays.asList());
    }

}
