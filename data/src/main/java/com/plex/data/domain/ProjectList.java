package com.plex.data.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ProjectList {

  @Id
  private Long id;
  private String name;

  @ManyToMany
  private Set<Project> projects;
  //TODO Add: End + start date?
  //TODO add: Students
}