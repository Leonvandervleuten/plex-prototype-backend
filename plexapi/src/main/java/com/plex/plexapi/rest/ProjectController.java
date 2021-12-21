package com.plex.plexapi.rest;

import com.plex.plex.domain.Project;
import com.plex.plex.domain.ProjectList;
import com.plex.plex.service.ProjectService;
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
  @GetMapping("/projects")
  public List<Project> getChallenges() {
    return projectService.getChallenges();
  }

  @CrossOrigin
  @GetMapping("/projects/{id}")
  public Project getChallengesById(@PathVariable("id") String id) {
    return projectService.getChallengeById(Long.parseLong(id));
  }

  @CrossOrigin
  @PostMapping(value = "/projectlist")
  public String getData(@RequestBody ProjectList newChallengeList) {
    projectService.createChallengeList(newChallengeList);
    // TODO Response 200
    return "OK";
  }

  @CrossOrigin
  @GetMapping("/projectlist/student")
  public List<Project> getChallengeListForStudent() {
    return projectService.challengeListForStudent();
  }
}
