package com.plex.plex.repository;

import com.plex.plex.domain.Project;
import org.springframework.stereotype.Service;

@Service
public class DefaultProjectDataStrategy extends DefaultDataStrategy<Project, Long> implements ProjectDataStategy {

  private static Long ID = 1L;

  @Override
  protected Long getNextAvailableId() {
    return ID++;
  }
}