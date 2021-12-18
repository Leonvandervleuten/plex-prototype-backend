package com.plex.data.repository;

import com.plex.plex.domain.Project;
import com.plex.plex.repository.ProjectDataStategy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> , ProjectDataStategy {

}