package com.andigital.andservice.read.api.repository;

import com.andigital.andservice.read.api.domain.Project;
import com.andigital.andservice.read.api.domain.ProjectsResponse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by aditeja on 5/23/2017.
 */
public interface ProjectsRepository extends MongoRepository<Project, String> {


	List<ProjectsResponse> getProjectsResponse();
	
	
}
