package com.andigital.andservice.services;

import com.andigital.andservice.common.Constant;
import com.andigital.andservice.exception.ANDApplicationException;
import com.andigital.andservice.model.DashboardResponse;
import com.andigital.andservice.model.domain.Project;
import com.andigital.andservice.model.domain.User;
import com.andigital.andservice.repository.ProjectRepository;
import com.andigital.andservice.services.impl.ProjectServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

/**
 * Created by rakeshchoudhary on 5/24/17.
 */
public class ProjectServiceTest{

    @InjectMocks
    private ProjectService projectService = new ProjectServiceImpl();

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private UserService userService;
    private Project testProject;
    private DashboardResponse testDashboardResponse;
    private User testSDM;
    private User testUser1;
    private User testUser2;
    private List<User> testUsers;

    /**
     * Inits the mocks.
     */
    @BeforeMethod
    public void setUp(Method method) throws Exception{
        MockitoAnnotations.initMocks(this);
        testProject = new Project();
        testDashboardResponse= new DashboardResponse();
        testSDM = new User();
        testUsers= new ArrayList<>();
        testUser1 = new User();
        testUser1.setUserId("1");
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
        testDashboardResponse.setProject(testProject);
        testSDM.setUserId("2");
        testSDM.setEmail("test@email.com");
        testSDM.setRole(Constant.SDM);
        testDashboardResponse.setActualSpent("110K");
        testDashboardResponse.setForecastSpent("+10k");
        testDashboardResponse.setServiceDeliveryManager(testSDM);
    }

    /**
     * Test find user by id.
     */
    @Test
    public void testGetProjectByIdSuccessResponse() throws Exception{
        when(projectRepository.findById("1")).thenReturn(testProject);
        when(userService.getUserByUserId("1")).thenReturn(testUser1);
        when(userService.getUserByUserId("2")).thenReturn(testSDM);
        when(userService.getUserByUserId("3")).thenReturn(testUser2);
        DashboardResponse dashboardResponse = projectService.getProjectById("1");
        verify(projectRepository,atMost(1)).findById("1");
        verify(userService,atMost(1)).getUserByUserId("2");
        assertEquals(dashboardResponse.getProject().getTitle(), this.testProject.getTitle());
        assertNotNull(dashboardResponse.getServiceDeliveryManager());
    }

    @Test(expectedExceptions = {ANDApplicationException.class})
    public void testFindUserByIdExceptionResponse() throws Exception {
        Mockito.doThrow(new ANDApplicationException()).when(projectRepository).findById("1");
        projectService.getProjectById("1");
    }
}
