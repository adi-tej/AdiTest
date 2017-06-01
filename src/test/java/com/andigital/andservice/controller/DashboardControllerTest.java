package com.andigital.andservice.controller;

import com.andigital.andservice.common.Constant;
import com.andigital.andservice.model.DashboardResponse;
import com.andigital.andservice.model.domain.Client;
import com.andigital.andservice.model.domain.Project;
import com.andigital.andservice.model.domain.SystemProperties;
import com.andigital.andservice.model.domain.User;
import com.andigital.andservice.services.ClientService;
import com.andigital.andservice.services.ProjectService;
import com.andigital.andservice.services.SystemPropertiesService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.restdocs.ManualRestDocumentation;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Unit Tests for the Dashboard controller.
 */
public class DashboardControllerTest{

    private MockMvc mockMvc;

    @Mock
    private SystemPropertiesService systemPropertiesService;
    @Mock
    private ProjectService projectService;

    @Mock
    private ClientService clientService;

    @InjectMocks
    private DashboardController dashboardController;

    private List<SystemProperties> systemPropertiesResponse;
    private List<Client> clientResponse;
    private static final String USER_ID = "0123450";
    private static final String USER_ID_INCORRECT = "012350";
    private final ManualRestDocumentation restDocumentation = new ManualRestDocumentation("build/docs/generated-snippets");

    private Project testProject;
    private DashboardResponse testDashboardResponse;
    private User testSDM;
    private User testUser1;
    private User testUser2;
    private List<User> testUsers;

    /**
     * Inits the responses for mocks.
     */
    public void setupTestProjectAndUsers(){
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
     * Sets up.
     * @param method the method
     */
    @BeforeClass
    public void setUp(Method method) throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(dashboardController)
                .apply(documentationConfiguration(this.restDocumentation)).build();
        this.restDocumentation.beforeTest(getClass(), method.getName());
        systemPropertiesResponse = Arrays.asList(new SystemProperties("12786328",new SimpleDateFormat(Constant.DATE_FORMAT_DD_MM_YYYY).parse("05/27/2017")));
        clientResponse = Arrays.asList(new Client("12345", "clientName", "Jan-Dec", Arrays.asList(new Project( "projectName",null, new SimpleDateFormat(Constant.DATE_FORMAT_DD_MM_YYYY).parse("02/23/2017"), new SimpleDateFormat("dd/MM/yyyy").parse("03/23/2017"),null,null))));
    }

    /**
     * Test dashboard api.
     * @throws Exception the exception
     */
    @Test
    public void testDashboardApi() throws Exception {
        this.mockMvc.perform(get("/dashboard/test").contentType(MediaType.TEXT_PLAIN)).andExpect(status().isOk())
                .andDo(print()).andExpect(content().string(containsString("Rest Service is up and running for DashBoard!!")))
                .andDo(document("dashboard"));
    }
    @Test
    public void testGetProjectByIdSuccessResponse() throws Exception {
        when(projectService.getProjectById(testProject.getId())).thenReturn(testDashboardResponse);
        mockMvc.perform(get("/dashboard/project/" + testProject.getId()).contentType(MediaType.TEXT_PLAIN)).
                andExpect(status().isOk())
                .andExpect(jsonPath("$.dashboard").exists())
                .andExpect(jsonPath("$.dashboard.serviceDeliveryManager").exists())
                .andDo(document("dashboard-project", responseFields(
                        fieldWithPath("status").description("Status of the rest call"),
                        fieldWithPath("dashboard.project").description("Result of the rest call"),
                        fieldWithPath("dashboard.actualSpent").description("Actual spent amount on the project"),
                        fieldWithPath("dashboard.forecastSpent").description(""),
                        fieldWithPath("dashboard.forecastVariance").description("Variance in percentage between forecast and actual"),
                        fieldWithPath("dashboard.schedule").description("An array of actual activities of team members for current year"),
                        fieldWithPath("dashboard.project").description("The data related to the selected project"),
                        fieldWithPath("dashboard.project.id").description("Id of the project"),
                        fieldWithPath("dashboard.project.title").description("Title of the project"),
                        fieldWithPath("dashboard.project.clientId").description("Client id of client by whom the project is owned by"),
                        fieldWithPath("dashboard.project.startDate").description("Start date of the project"),
                        fieldWithPath("dashboard.project.endDate").description("Actual end date of the project"),
                        fieldWithPath("dashboard.project.plannedBudget").description("The budget allocated to the project"),
                        fieldWithPath("dashboard.project.users").description("An array of team members"),
                        fieldWithPath("dashboard.project.users[].id").description("User id of team member"),
                        fieldWithPath("dashboard.project.users[].firstName").description("First name of team member"),
                        fieldWithPath("dashboard.project.users[].lastName").description("Last name of team member"),
                        fieldWithPath("dashboard.project.users[].role").description("Role of the member in team"),
                        fieldWithPath("dashboard.project.users[].email").description("Email id of team member"),
                        fieldWithPath("dashboard.project.users[].mobile").description("Mobile number of team member"),
                        fieldWithPath("dashboard.project.users[].profileImg").description("profile img of team member"),
                        fieldWithPath("dashboard.project.users[].profileDescription").description("Complete description of user "),
                        fieldWithPath("dashboard.project.users[].startDate").description("Start date of member in the project "),
                        fieldWithPath("dashboard.project.users[].endDate").description("End date of member in the project"),
                        fieldWithPath("dashboard.serviceDeliveryManager").description("Service delivery manager of the project"),
                        fieldWithPath("dashboard.serviceDeliveryManager.id").description("User id of sdm"),
                        fieldWithPath("dashboard.serviceDeliveryManager.firstName").description("First name of sdm"),
                        fieldWithPath("dashboard.serviceDeliveryManager.lastName").description("Last name of sdm"),
                        fieldWithPath("dashboard.serviceDeliveryManager.email").description("Email id of sdm"),
                        fieldWithPath("dashboard.serviceDeliveryManager.mobile").description("Mobile num of sdm"),
                        fieldWithPath("dashboard.serviceDeliveryManager.profileImg").description("Url of profile img of user"),
                        fieldWithPath("dashboard.serviceDeliveryManager.profileDescription").description("Complete description of user "),
                        fieldWithPath("dashboard.serviceDeliveryManager.startDate").description("Start date of member in the project  "),
                        fieldWithPath("dashboard.serviceDeliveryManager.endDate").description("End date of member in the project "))));
    }

    /*
     * UNIT TESTS FOR SYSTEM_PROPERTIES API ENDPOINT FOR REST DOCS DOCUMENTATION
     */
    /**
     * System properties API unit test success.
     * @throws Exception the exception
     */
    @Test
    public void testGetSystemPropertiesForSuccessResponse() throws Exception {
        when(systemPropertiesService.getSystemProperties()).thenReturn(systemPropertiesResponse);
        this.mockMvc.perform(get("/dashboard/systemproperties").accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andExpect(jsonPath("status",equalTo("success")))
                .andDo(document("systemPropertiesSuccess",responseFields(
                        fieldWithPath("status").description("This will give status 'success' for API request."),
                        fieldWithPath("systemproperties.id").description("This is MongoDB Id for System Properties."),
                        fieldWithPath("systemproperties.lastUpdatedDate").description("This is the System last Updated Date.")
                )));
    }

    /**
     * System properties API unit test error.
     * @throws Exception the exception
     */
    @Test
    public void testGetSystemPropertiesForErrorResponse() throws Exception {
        when(systemPropertiesService.getSystemProperties()).thenReturn(null);
        this.mockMvc.perform(get("/dashboard/systemproperties").accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andExpect(jsonPath("status",equalTo("error")))
                .andDo(document("systemPropertiesError",responseFields(
                        fieldWithPath("status").description("This will give status 'error' for API request."),
                        fieldWithPath("error.message").description("This is Error Message in case of any error."),
                        fieldWithPath("error.code").description("This is Error Code representing the error.")
                )));

    }
    @Test
    public void testGetProjectByIdErrorResponse() throws Exception {
        String projectId = "-91";
        mockMvc.perform(get("/dashboard/project/" + projectId).contentType(MediaType.TEXT_PLAIN)).
                andExpect(status().isOk())
                .andExpect(jsonPath("$.project.project").doesNotExist());
    }

    /*
     * UNIT TESTS FOR CLIENT_PROJECTS API ENDPOINT FOR REST DOCS DOCUMENTATION
     */
    /**
     * Client projects API unit test success.
     * @throws Exception the exception
     */
    @Test
    public void testGetProjectsListForSuccessResponse() throws Exception{
        when(clientService.getClientProjectsByUserId(USER_ID)).thenReturn(clientResponse);
        this.mockMvc.perform(get("/dashboard/user/"+USER_ID+"/projects").accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andExpect(jsonPath("status",equalTo("success")))
                .andDo(document("clientProjectsSuccess",responseFields(
                        fieldWithPath("status").description("This will give status 'success' for API request."),
                        fieldWithPath("clients[].clientId").description("This is the Id for Client to which user of userId:2343249202 belongs to."),
                        fieldWithPath("clients[].name").description("This is Name of the Client."),
                        fieldWithPath("clients[].budgetYear").description("This is Financial Year of the client."),
                        fieldWithPath("clients[].projects[]").description("This gives the list of projects under the Client."),
                        fieldWithPath("clients[].projects[].projectId").description("This is the Id of the project."),
                        fieldWithPath("clients[].projects[].clientId").description("This is the Id of the Client of the project."),
                        fieldWithPath("clients[].projects[].title").description("This is Name of the Project."),
                        fieldWithPath("clients[].projects[].startDate").description("This is Start Date of the Project."),
                        fieldWithPath("clients[].projects[].endDate").description("This is End Date of the Project."),
                        fieldWithPath("clients[].projects[].plannedBudget").description("This is the Planned budget of the Project."),
                        fieldWithPath("clients[].projects[].users").description("This is list of users working in the Project.")
                )));

    }

    /**
     * Client projects API unit test error.
     * @throws Exception the exception
     */
    @Test
    public void testGetProjectsListForErrorResponse() throws Exception {
        this.mockMvc.perform(get("/dashboard/user/"+USER_ID_INCORRECT+"/projects").accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andExpect(jsonPath("status",equalTo("error")))
                .andDo(document("clientProjectsError",responseFields(
                        fieldWithPath("status").description("This will give status 'error' for API request."),
                        fieldWithPath("error.message").description("This is Error Message in case of any error."),
                        fieldWithPath("error.code").description("This is Error Code representing the error.")
                )));
    }

    /**
     * Tear down.
     */
    @AfterClass
    public void tearDown() {
        this.restDocumentation.afterTest();
    }

}
