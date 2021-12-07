package com.plex.data.domain;

import java.util.List;

public class Challenge {

  private Long id;
  private String title;
  private String shortDescription;
  private List<Category> categories;
  private String uploader;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getShortDescription() {
    return shortDescription;
  }

  public void setShortDescription(String shortDescription) {
    this.shortDescription = shortDescription;
  }

  public List<Category> getCategories() {
    return categories;
  }

  public void setCategories(List<Category> categories) {
    this.categories = categories;
  }

  public String getUploader() {
    return uploader;
  }

  public void setUploader(String uploader) {
    this.uploader = uploader;
  }
}