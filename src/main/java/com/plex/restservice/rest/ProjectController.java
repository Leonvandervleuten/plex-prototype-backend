package com.plex.restservice.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.plex.restservice.service.ProjectService;
import com.plex.restservice.dto.NameAndIdList;
import com.plex.restservice.domain.Project;
import org.json.JSONException;
import org.json.JSONObject;
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
  public String getData(@RequestBody NameAndIdList nameAndIdList) throws JSONException, JsonProcessingException {
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    String json = ow.writeValueAsString(nameAndIdList);
    JSONObject object = new JSONObject(json);
    return projectService.createProjectList(object).toString();
  }

  @CrossOrigin
  @GetMapping("/projectlist/student")
  public List<Project> getProjectListForStudent() {
    return projectService.projectListForStudent();
  }
}
