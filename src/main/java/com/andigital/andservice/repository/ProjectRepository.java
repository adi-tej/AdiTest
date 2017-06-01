package com.andigital.andservice.repository;

import com.andigital.andservice.model.domain.Project;
import org.springframework.data.mongodb.repository.MongoRepository;


/**
 * Created by rakeshchoudhary on 5/24/17.
 */
public interface ProjectRepository  extends MongoRepository<Project, String> {
     
     /**
      * Find by project id.
      *
      * @param projectId the project id
      * @return the project
      */
      Project findById(String projectId) throws Exception;
};
