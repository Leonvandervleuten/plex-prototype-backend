package com.plex.plexapi.rest;

import com.plex.plex.domain.ProjectList;
import com.plex.plex.service.ProjectListService;
import com.plex.plex.domain.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1")
public class ProjectListController {

  private ProjectListService projectListService;

  @Autowired
  public ProjectListController(ProjectListService projectListService) {
    this.projectListService = projectListService;
  }

  @CrossOrigin
  @GetMapping("/projectlist")
  public List<ProjectList> getProjects() {
    return projectListService.findAllProjectsList();
  }

  @CrossOrigin
  @PostMapping(path = "/projectlist/add", consumes = { "application/json" })
  public void saveProjectList(@RequestBody ProjectList projectList) {
    projectListService.makeNewProjectList(projectList);
  }

  @CrossOrigin
  @GetMapping("/projectlist/latest")
  public List<Project> getProjectById() {
    return projectListService.findLatest();
  }
}
