package com.plex.restservice.domain;

import java.util.Date;
import java.util.List;

public class Project {
  public Long id;
  public User user;
  public Long userId;
  public String name;
  public String description;
  public String shortDescription;
  public String uri;
  public List<Collaborator> collaborators;
  public List<LinkedInstitution> linkedInstitutions;
  public Date created;
  public Date updated;
  public ProjectIcon projectIcon; //TODO add
  public List<CallToAction> callToActions;
  public List<Like> likes;
  public List<Image> images;
  public boolean institutePrivate;
  public List<Category> categories;

  //Used for /project/{id} request
  public Project(Long id, Long userId, String name, String description, String shortDescription, String uri,
      boolean institutePrivate) {
    this.id = id;
    this.userId = userId;
    this.name = name;
    this.description = description;
    this.shortDescription = shortDescription;
    this.uri = uri;
    this.institutePrivate = institutePrivate;
  }

  //Used for /project request
  public Project(Long id, String name, String shortDescription) {
    this.id = id;
    this.name = name;
    this.shortDescription = shortDescription;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public void setCategories(List<Category> categories) {
    this.categories = categories;
  }

  @Override
  public String toString() {
    return "Projects{" +
        "id=" + id +
        ", user=" + user +
        ", userId=" + userId +
        ", name='" + name + '\'' +
        ", description='" + description + '\'' +
        ", shortDescription='" + shortDescription + '\'' +
        ", uri='" + uri + '\'' +
        ", collaborators=" + collaborators +
        ", linkedInstitutions=" + linkedInstitutions +
        ", created=" + created +
        ", updated=" + updated +
        ", projectIcon=" + projectIcon +
        ", callToActions=" + callToActions +
        ", likes=" + likes +
        ", images=" + images +
        ", institutePrivate=" + institutePrivate +
        ", categories=" + categories +
        '}';
  }
}
