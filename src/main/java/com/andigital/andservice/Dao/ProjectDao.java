package com.andigital.andservice.Dao;

import com.andigital.andservice.model.domain.Project;

/**
 * Created by rakeshchoudhary on 5/24/17.
 */
public interface ProjectDao {

     /**
      *@projectId is project if of the selected project.
      *This method lookups the projectRepo to collect all the data related to a unique projectId
     */
     Project getProjectById(String projectId) throws Exception;
}
