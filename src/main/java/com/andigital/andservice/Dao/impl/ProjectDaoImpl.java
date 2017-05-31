package com.andigital.andservice.Dao.impl;

import com.andigital.andservice.Dao.ProjectDao;
import com.andigital.andservice.model.domain.Project;
import com.andigital.andservice.repository.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by rakeshchoudhary on 5/24/17.
 */
@Service
public class ProjectDaoImpl implements ProjectDao {

    @Autowired
    ProjectRepository projectRepository;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * @projectId is unique projectId.
     * This method lookups the projectRepo to collect all the data related to a unique projectId
     */
    @Override
    public Project getProjectById(String projectId) throws Exception {
        logger.debug("Getting project details: {}",projectId);
        return projectRepository.findByProjectId(projectId);
    }
}
