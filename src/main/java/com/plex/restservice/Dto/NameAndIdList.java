package com.plex.restservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class NameAndIdList {

  private String listName;

  @JsonProperty("projectids")
  private List<ProjectIds> projectIds;

  public String getListName() {
    return listName;
  }

  public void setListName(String listName) {
    this.listName = listName;
  }

  public List<ProjectIds> getProjectIds() {
    return projectIds;
  }

  public void setProjectIds(List<ProjectIds> projectIds) {
    this.projectIds = projectIds;
  }
}
