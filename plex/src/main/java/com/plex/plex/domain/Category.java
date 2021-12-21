package com.plex.plex.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Category {

  @Id
  private Long id;
  private String name;

  @ManyToMany(mappedBy = "categories")
  @JsonIgnore
  private Set<Project> projects;
}