package com.plex.plex.service;

import com.plex.plex.domain.Project;
import com.plex.plex.domain.ProjectList;
import com.plex.plex.repository.ProjectDataStategy;
import com.plex.plex.repository.ProjectListDataStretegy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProjectService {

  private final ProjectDataStategy projectDataStategy;
  private final ProjectListDataStretegy projectListDataStretegy;

  @Autowired
  public ProjectService(
      ProjectDataStategy projectDataStategy,
      ProjectListDataStretegy projectListDataStretegy
  ) {
    this.projectDataStategy = projectDataStategy;
    this.projectListDataStretegy = projectListDataStretegy;
  }

  public List<Project> getChallenges() {
    return projectDataStategy.findAll();
  }

  private boolean updateProjectsFromDex() {
    return projectDataStategy.findAll().isEmpty();
  }

  public Project getChallengeById(Long id) {
    return projectDataStategy.findById(id).orElse(null);
  }

  public Set<Project> selectProjectsBasedOnID(List<Long> challengeIds) {
    return getChallenges()
        .stream()
        .filter(challenge -> challengeIds.contains(challenge.getId()))
        .collect(Collectors.toSet());
  }

  public void createChallengeList(ProjectList projectList) {
    projectListDataStretegy.save(projectList);
  }

  public List<Project> challengeListForStudent() {
    return projectListDataStretegy.findAll().stream()
        .max(Comparator.comparing(ProjectList::getId))
        .map(ProjectList::getProjects).orElse(Collections.emptySet())
        .stream().toList();
  }
}