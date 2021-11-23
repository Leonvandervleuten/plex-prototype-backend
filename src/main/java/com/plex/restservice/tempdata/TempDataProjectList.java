package com.plex.restservice.tempdata;

import com.plex.restservice.domain.Project;

import java.util.Collections;
import java.util.List;

public class TempDataProjectList {

  public String name = "No list created";
  public List<Project> tempProjectList = Collections.emptyList();

  public TempDataProjectList() {
  }

  public List<Project> getTempProjectList() {
    return tempProjectList;
  }

  public void setMockProjectList(String name, List<Project> mockProjectList) {
    this.name = name;
    this.tempProjectList = mockProjectList;
  }
}
