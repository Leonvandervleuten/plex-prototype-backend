package com.plex.dexapi.service;

import com.plex.data.domain.Category;
import com.plex.data.domain.Project;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProjectServiceMock {

  public List<Project> getChallenges() {
    Category category1 = new Category();
    category1.setId(3L);
    category1.setName("Code");
    Category category2 = new Category();
    category2.setId(7L);
    category2.setName("UX/UI");
    Category category3 = new Category();
    category3.setId(11L);
    category3.setName("Infra");

    List<Project> projects = new ArrayList<>(5);
    projects.add(createProject(1L, category1, category2));
    projects.add(createProject(2L, category2, category3));
    projects.add(createProject(3L, category1, category3));
    projects.add(createProject(4L, category2, category1));
    projects.add(createProject(5L, category1, category2, category3));

    return projects;
  }

  private Project createProject(Long id, Category... category) {
    Set<Category> categories = new HashSet<>(category.length);
    categories.addAll(Arrays.asList(category));

    Project project = new Project();
    project.setId(id);
    project.setTitle("Title " + id);
    project.setUploader("Uploader " + id);
    project.setShortDescription("Description " + id);
    project.setCategories(categories);

    return project;
  }
}
