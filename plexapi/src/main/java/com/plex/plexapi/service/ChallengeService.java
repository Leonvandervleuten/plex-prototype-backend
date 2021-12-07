package com.plex.plexapi.service;

import com.plex.data.domain.Project;
import com.plex.data.domain.ProjectList;
import com.plex.data.repository.ChallengeListRepository;
import com.plex.data.repository.ProjectRepository;
import com.plex.dexapi.service.ProjectService;
import com.plex.plexapi.domain.NewChallengeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ChallengeService {

  private final ProjectRepository projectRepository;
  private final ChallengeListRepository challengeListRepository;
  private final ProjectService projectService;

  @Autowired
  public ChallengeService(
      ProjectRepository projectRepository,
      ChallengeListRepository challengeListRepository,
      ProjectService projectService
  ) {
    this.projectRepository = projectRepository;
    this.challengeListRepository = challengeListRepository;
    this.projectService = projectService;
  }

  public List<Project> getChallenges() {
    if (updateProjectsFromDex()) {
      projectRepository.saveAll(projectService.getChallenges());
    }
    return projectRepository.findAll();
  }

  private boolean updateProjectsFromDex() {
    return projectRepository.findAll().isEmpty();
  }

  public Project getChallengeById(Long id) {
    return projectRepository.findById(id).orElse(null);
  }

  public Set<Project> selectProjectsBasedOnID(List<Long> challengeIds) {
    return getChallenges()
        .stream()
        .filter(challenge -> challengeIds.contains(challenge.getId()))
        .collect(Collectors.toSet());
  }

  public void createChallengeList(NewChallengeList newChallengeList) {
    Set<Project> projects = selectProjectsBasedOnID(newChallengeList.getChallengeIds());
    ProjectList projectList = new ProjectList();
    projectList.setName(newChallengeList.getName());
    projectList.setProjects(projects);
    challengeListRepository.save(projectList);
  }

  public List<Project> challengeListForStudent() {
    return challengeListRepository.findAll().stream()
        .max(Comparator.comparing(ProjectList::getId))
        .map(ProjectList::getProjects).orElse(Collections.emptySet())
        .stream().toList();
  }
}