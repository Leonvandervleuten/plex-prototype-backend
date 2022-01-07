package com.plex.plex.repository;

import com.plex.plex.domain.ProjectList;

public class DefaultProjectListDataStrategy extends DefaultDataStrategy<ProjectList, Long> implements ProjectListDataStretegy {

  private static Long ID = 1L;

  @Override
  protected Long getNextAvailableId() {
    return ID++;
  }
}