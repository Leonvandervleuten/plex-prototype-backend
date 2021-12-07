package com.plex.data.repository;

import com.plex.data.domain.ProjectList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChallengeListRepository extends JpaRepository<ProjectList, Long> {

}