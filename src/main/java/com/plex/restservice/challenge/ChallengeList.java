package com.plex.restservice.challenge;

import java.util.List;

public class ChallengeList {
    private long id;
    private final List<Long> challengeIds;
    //TODO Add: End + start date?
    //TODO add: Students


    public ChallengeList(long id, List<Long> challengeIds) {
        this.id = id;
        this.challengeIds = challengeIds;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Long> getChallengeIds() {
        return challengeIds;
    }
}
