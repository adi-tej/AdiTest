package com.andigital.andservice.repository;

import com.andigital.andservice.domain.Project;
import com.andigital.andservice.domain.ProjectsResponse;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


/**
 * The interface Projects repository.
 */
public interface ProjectsRepository extends MongoRepository<Project, String> {


	List<ProjectsResponse> getProjectsResponse();
}
