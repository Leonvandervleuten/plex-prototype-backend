package com.plex.plexapi.rest;

import com.plex.data.domain.Challenge;
import com.plex.plexapi.domain.NewChallengeList;
import com.plex.plexapi.service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1")
public class ProjectController {

  private final ChallengeService challengeService;

  @Autowired
  public ProjectController(ChallengeService challengeService) {
    this.challengeService = challengeService;
  }

  @CrossOrigin
  @GetMapping("/challenges")
  public List<Challenge> getChallenges() {
    return challengeService.getChallenges();
  }

  @CrossOrigin
  @GetMapping("/projects/{id}")
  public Challenge getChallengesById(@PathVariable("id") String id) {
    return challengeService.getChallengeById(Long.parseLong(id));
  }

  @CrossOrigin
  @PostMapping(value = "/projectlist")
  public String getData(@RequestBody NewChallengeList newChallengeList) {
    challengeService.createChallengeList(newChallengeList);
    // TODO Response 200
    return "OK";
  }

  @CrossOrigin
  @GetMapping("/projectlist/student")
  public List<Challenge> getChallengeListForStudent() {
    return challengeService.challengeListForStudent();
  }
}
