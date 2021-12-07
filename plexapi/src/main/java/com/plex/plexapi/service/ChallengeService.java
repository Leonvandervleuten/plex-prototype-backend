package com.plex.plexapi.service;

import com.plex.data.domain.Challenge;
import com.plex.data.domain.ChallengeList;
import com.plex.data.repository.ChallengeListRepository;
import com.plex.data.repository.ChallengeRepository;
import com.plex.dexapi.service.ProjectService;
import com.plex.plexapi.domain.NewChallengeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChallengeService {

  private final ChallengeRepository challengeRepository;
  private final ChallengeListRepository challengeListRepository;
  private final ProjectService projectService;

  @Autowired
  public ChallengeService(
      ChallengeRepository challengeRepository,
      ChallengeListRepository challengeListRepository,
      ProjectService projectService
  ) {
    this.challengeRepository = challengeRepository;
    this.challengeListRepository = challengeListRepository;
    this.projectService = projectService;
  }

  public List<Challenge> getChallenges() {
    if (updateProjectsFromDex()) {
      challengeRepository.setChallenges(projectService.getChallenges());
    }
    return challengeRepository.getChallenges();
  }

  private boolean updateProjectsFromDex() {
    return challengeRepository.getChallenges().isEmpty();
  }

  public Challenge getChallengeById(Long id) {
    return challengeRepository.getChallenge(id);
  }

  public List<Challenge> selectProjectsBasedOnID(List<Long> challengeIds) {
    return getChallenges()
        .stream()
        .filter(challenge -> challengeIds.contains(challenge.getId()))
        .collect(Collectors.toList());

//    List<Integer> idList = makeListOfIDs(requestedList);
//    List<Project> fullProjectList = getChallenges();
//    List<Project> selectedProjects = new ArrayList<>();
//
//    for (int idFromIdList : idList) {
//      for (Project project : fullProjectList) {
//        int idFromFullProjectList = Math.toIntExact(project.id);
//        if (idFromIdList == idFromFullProjectList) {
//          selectedProjects.add(project);
//        }
//      }
//    }
//    return selectedProjects;
  }

  public void createChallengeList(NewChallengeList newChallengeList) {
    List<Challenge> challenges = selectProjectsBasedOnID(newChallengeList.getChallengeIds());
    challengeListRepository.addChallengeList(newChallengeList.getName(), challenges);
  }

  public List<Challenge> challengeListForStudent() {
    ChallengeList challengeList = challengeListRepository.getMostRecentChallengeList();
    return challengeList != null ? challengeList.getChallenges() : Collections.emptyList();
  }
}