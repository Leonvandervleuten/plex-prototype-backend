package com.plex.plex.service;

import com.plex.plex.domain.Project;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExternalProjectService {

  List<Project> getProjects();
}