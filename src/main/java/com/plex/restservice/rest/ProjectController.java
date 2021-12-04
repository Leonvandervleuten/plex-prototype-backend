package com.plex.restservice.rest;

import com.plex.restservice.domain.Challenge;
import com.plex.restservice.service.ProjectService;
import com.plex.restservice.dto.NewChallengeListDTO;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1")
public class ProjectController {

  private final ProjectService projectService;

  @Autowired
  public ProjectController(ProjectService projectService) {
    this.projectService = projectService;
  }

  @CrossOrigin
  @GetMapping("/challenges")
  public List<Challenge> getChallenges() {
    return projectService.getChallenges();
  }

  @CrossOrigin
  @GetMapping("/projects/{id}")
  public Challenge getChallengesById(@PathVariable("id") String id) {
    return projectService.getChallengeById(Long.parseLong(id));
  }

  @CrossOrigin
  @PostMapping(value = "/projectlist")
  public String getData(@RequestBody NewChallengeListDTO newProject) throws JSONException {
    projectService.createChallengeList(newProject);
    // TODO Response 200
    return "OK";
  }

  @CrossOrigin
  @GetMapping("/projectlist/student")
  public List<Challenge> getProjectListForStudent() {
    return projectService.challengeListForStudent();
  }
}
