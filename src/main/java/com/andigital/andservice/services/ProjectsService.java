package com.andigital.andservice.services;


import com.andigital.andservice.domain.Project;

import java.util.List;

/**
 * Created by aditeja on 5/23/2017.
 */
public interface ProjectsService {

    /**
     * API to return list of Projects
     *
     * @return projects
     * @throws Exception the exception
     */
    public List<Project> getProjects() throws Exception;

}