package com.andigital.andservice.services;

import com.andigital.andservice.domain.ClientsList;
import com.andigital.andservice.repository.ProjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * The type Projects service.
 */
@Service
public class ProjectsServiceImpl implements ProjectsService {

    /**
     * The Projects repository.
     */
    @Autowired
    private ProjectsRepository projectsRepository;

    /* (non-Javadoc)
     * @see com.andigital.andservice.services.DashboardService#getProjects()
     */
    @Override
    public List<ClientsList> getProjects(String userId) throws Exception {
    	return projectsRepository.getProjects(userId);
    }
}

