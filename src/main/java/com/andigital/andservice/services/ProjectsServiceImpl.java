package com.andigital.andservice.services;

import com.andigital.andservice.domain.Project;
import com.andigital.andservice.repository.ProjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by aditeja on 5/23/2017.
 */
@Service
public class ProjectsServiceImpl implements ProjectsService {
    /**
     * The Projects repository.
     */
    @Autowired
    ProjectsRepository projectsRepository;

    /**
     * Default constructor.
     */
    public ProjectsServiceImpl() {
        // TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
	 * @see com.andigital.andservice.read.api.services.DashboardService#getProjects()
	 */
    @Override
    public List<Project> getProjects() throws Exception {
        return projectsRepository.findAll();
    }
}
