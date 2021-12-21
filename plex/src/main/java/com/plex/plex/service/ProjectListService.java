package com.plex.plex.service;

import com.plex.plex.domain.Project;
import com.plex.plex.domain.ProjectList;
import com.plex.plex.repository.ProjectListDataStretegy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectListService {

  private final ProjectListDataStretegy projectListDataStretegy;

  @Autowired
  public ProjectListService(
      ProjectListDataStretegy projectListDataStretegy
  ) {
    this.projectListDataStretegy = projectListDataStretegy;
  }

  public List<ProjectList> findAllProjectsList() {
    return projectListDataStretegy.findAll();
  }

  public Optional<ProjectList> findProjectListById(String id){
    return projectListDataStretegy.findById(Long.parseLong(id));
  }

  public void makeNewProjectList(ProjectList projectList) {
    projectListDataStretegy.save(projectList);
  }

  public List<Project> findLatest(){
    return projectListDataStretegy.findAll().stream().max(Comparator.comparing(ProjectList::getId)).map(ProjectList::getProjects).orElse(
        Collections.emptySet()).stream().toList();
  }
}
