package com.plex.restservice;

import org.json.JSONObject;

class TempDataProjectList {

    public JSONObject mockProjectList;

    public TempDataProjectList() {
    }

    public JSONObject getMockProjectList() {
        return mockProjectList;
    }

    public void setMockProjectList(JSONObject mockProjectList) {
        this.mockProjectList = mockProjectList;
    }
}
