package com.andigital.andservice.services.impl;

import com.andigital.andservice.Dao.ProjectDao;
import com.andigital.andservice.common.Constant;
import com.andigital.andservice.exception.ANDApplicationException;
import com.andigital.andservice.model.DashboardResponse;
import com.andigital.andservice.model.domain.Project;
import com.andigital.andservice.model.domain.User;
import com.andigital.andservice.services.ProjectService;
import com.andigital.andservice.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by rakeshchoudhary on 5/24/17.
 */
@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ProjectDao projectDao;

    @Autowired
    UserService userService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     *@projectId is the input param, this method gathers the data from userService and project dao and returns
     * accumulated data as response
     */
    @Override
    public DashboardResponse getProjectById(String projectId) throws ANDApplicationException{
        logger.debug("In ProjectServiceImpl getting project:{}",projectId);
        DashboardResponse dashboardResponse = null;
        Project project = null;
        try {
            project = projectDao.getProjectById(projectId);
        } catch (Exception ex) {
            logger.error("An exception was thrown in ProjectDao {}",ex);
            throw new ANDApplicationException("ProjectDao failed with an exception");
        }
        if (project != null) {
            dashboardResponse = new DashboardResponse();
            User[] users = new User[project.getUsers().length];
            int index = 0;
            for (User user : project.getUsers()) {
                users[index] = userService.getUserByUserId(user.getUserId());
                if (users[index]!=null && users[index].getRole() != null && users[index].getRole().equals(Constant.SDM))
                    dashboardResponse.setServiceDeliveryManager(users[index]);
                index += 1;
            }
            project.setUsers(users);
            dashboardResponse.setProject(project);
            logger.debug("Returning project details:{} " ,dashboardResponse);
        }
        return dashboardResponse;
    }
}
