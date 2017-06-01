package com.andigital.andservice.services.impl;

import com.andigital.andservice.common.Constant;
import com.andigital.andservice.exception.ANDApplicationException;
import com.andigital.andservice.model.DashboardResponse;
import com.andigital.andservice.model.domain.Project;
import com.andigital.andservice.model.domain.User;
import com.andigital.andservice.repository.ProjectRepository;
import com.andigital.andservice.services.ProjectService;
import com.andigital.andservice.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rakeshchoudhary on 5/24/17.
 */
@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UserService userService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     *@projectId is the input param, this method gathers the data from userService and project repository and returns
     * accumulated data as response
     */
    @Override
    public DashboardResponse getProjectById(String projectId) throws ANDApplicationException{
        logger.debug("In ProjectServiceImpl getting project:{}",projectId);
        DashboardResponse dashboardResponse = null;
        Project project = null;
        try {
            project = projectRepository.findById(projectId);
        } catch (Exception ex) {
            logger.error("An exception was thrown in ProjectRepository {}",ex);
            throw new ANDApplicationException("ProjectRepository failed with an exception",ex);
        }
        if (project != null) {
            dashboardResponse = new DashboardResponse();
            List<User> responseUsers = new ArrayList<>();
            for (User user : project.getUsers()) {
                User tempUser = userService.getUserByUserId(user.getUserId());
                tempUser.setRole(user.getRole());
                tempUser.setStartDate(user.getStartDate());
                tempUser.setEndDate(user.getEndDate());
                if (tempUser.getRole() != null && tempUser.getRole().equals(Constant.SDM))
                    dashboardResponse.setServiceDeliveryManager(tempUser);
                else responseUsers.add(tempUser);
            }
            project.setUsers(responseUsers);
            dashboardResponse.setProject(project);
            logger.debug("Returning project details:{} " ,dashboardResponse);
        }
        return dashboardResponse;
    }
}
