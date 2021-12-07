package com.plex.data.repository;

import com.plex.data.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

}