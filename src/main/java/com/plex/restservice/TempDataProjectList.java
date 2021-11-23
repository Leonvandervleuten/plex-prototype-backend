package com.plex.restservice;

import com.plex.restservice.challenge.Challenge;

import java.util.Collections;
import java.util.List;

class TempDataProjectList {

    public String name = "No list created";
    public List<Challenge> mockProjectList = Collections.emptyList();

    public TempDataProjectList() {
    }

    public List<Challenge> getMockProjectList() {
        return mockProjectList;
    }

    public void setMockProjectList(String name, List<Challenge> mockProjectList) {
        this.name = name;
        this.mockProjectList = mockProjectList;
    }
}