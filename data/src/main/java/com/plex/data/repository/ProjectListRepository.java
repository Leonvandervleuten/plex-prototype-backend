package com.plex.data.repository;

import com.plex.plex.domain.ProjectList;
import com.plex.plex.repository.ProjectListDataStretegy;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public interface ProjectListRepository extends JpaRepository<ProjectList, Long> , ProjectListDataStretegy {

}