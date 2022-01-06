package com.plex.plex.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.plex.plex.repository.Identifiable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Project implements Identifiable<Long> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String shortDescription;
  private String uploader;

  @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
  private Set<Category> categories;

  @ManyToMany(mappedBy = "projects")
  @JsonIgnore
  private Set<ProjectList> projectLists;
}