package com.andigital.andservice.services;

import com.andigital.andservice.BaseTestNG;
import com.andigital.andservice.common.Constant;
import com.andigital.andservice.model.DashboardResponse;
import com.andigital.andservice.model.domain.Project;
import com.andigital.andservice.model.domain.User;
import com.andigital.andservice.repository.ProjectRepository;
import com.andigital.andservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertNull;

/**
 * Created by rakeshchoudhary on 5/24/17.
 */
public class ProjectServiceIntegrationTest extends BaseTestNG{

    @Autowired
    ProjectService projectService;

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserRepository userRepository;
    private Project testProject;
    private User testSDM;
    private User testUser1;
    private User testUser2;
    private List<User> testUsers;

    @BeforeClass
    public void setup(){
        testProject = new Project();
        testSDM = new User();
        testUsers= new ArrayList<>();
        testUser1 = new User();
        testUser1.setUserId("1");
        testUser1.setId("1");
        testUser1.setRole("Analyst");
        testUser1.setEmail("Jon@email.com");
        testUser1.setFirstName("John");
        testUser1.setLastName("Snow");
        String userStartDateStr = "01-01-2017";
        String userEndDateStr = "01-01-2018";
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date userStartDate1 = null;
        Date userEndDate1 = null;
        try {
            userStartDate1 = dateFormat.parse(userStartDateStr);
            userEndDate1 = dateFormat.parse(userEndDateStr);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        testUser1.setStartDate(userStartDate1);
        testUser1.setEndDate(userEndDate1);
        testUser1.setMobile("012891945691");
        testUser1.setProfileDescription("Jon's work life description...");
        testUser2 = new User();
        testUser2.setUserId("3");
        testUser2.setId("3");
        testUser2.setRole("Senior_Analyst");
        testUser2.setEmail("Arya@email.com");
        testUser2.setFirstName("Arya");
        testUser2.setLastName("Stark");
        userStartDateStr = "01-01-2017";
        userEndDateStr = "01-01-2018";
        Date userStartDate2 = null;
        Date userEndDate2 = null;
        try {
            userStartDate2 = dateFormat.parse(userStartDateStr);
            userEndDate2 = dateFormat.parse(userEndDateStr);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        testUser2.setStartDate(userStartDate2);
        testUser2.setEndDate(userEndDate2);
        testUser2.setMobile("812891945691");
        testUser2.setProfileDescription("Arya's work life description...");
        testSDM.setUserId("2");
        testSDM.setId("2");
        testSDM.setEmail("test@email.com");
        testSDM.setRole(Constant.SDM);
        testUsers.add(testUser1);
        testUsers.add(testUser2);
        testUsers.add(testSDM);
        testProject.setId("323245");
        testProject.setTitle("COSTA");
        testProject.setPlannedBudget("100K");
        testProject.setClientId("0123456");
        String startDateStr = "01-01-2017";
        String endDateStr = "01-01-2018";
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = dateFormat.parse(startDateStr);
            endDate = dateFormat.parse(endDateStr);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        testProject.setStartDate(startDate);
        testProject.setEndDate(endDate);
        testProject.setUsers(testUsers);
        userRepository.save(testUser1);
        userRepository.save(testUser2);
        userRepository.save(testSDM);
        projectRepository.save(testProject);
    }

    /**
     * Gets the project by id happy path.
     *
     * @return the project by id happy path
     * @throws Exception the exception
     */
    @Test
    public void testGetProjectByIdSuccessResponse() throws Exception {
        String projectId = testProject.getId();
        DashboardResponse dashboardResponse = projectService.getProjectById(projectId);
        System.out.println(dashboardResponse);
        assertNotNull(dashboardResponse);
    }

    /**
     * Gets the project by id sad path.
     *
     * @return the project by id sad path
     * @throws Exception the exception
     */
    @Test
    public void testGetProjectByIdErrorResponse() throws Exception {
        String projectId = "-91";
        DashboardResponse dashboardResponse = projectService.getProjectById(projectId);
        assertNull(dashboardResponse);
    }

    @AfterClass
    public void cleanup() throws Exception{
        userRepository.deleteAll();
        projectRepository.deleteAll();
    }
}
