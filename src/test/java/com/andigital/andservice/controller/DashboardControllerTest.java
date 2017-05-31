package com.andigital.andservice.controller;

import com.andigital.andservice.common.Constant;
import com.andigital.andservice.model.domain.Client;
import com.andigital.andservice.model.domain.Project;
import com.andigital.andservice.model.domain.SystemProperties;
import com.andigital.andservice.services.ClientService;
import com.andigital.andservice.services.SystemPropertiesService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.restdocs.ManualRestDocumentation;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Arrays;
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
    private ClientService clientService;

    @InjectMocks
    private DashboardController dashboardController;

    private List<SystemProperties> systemPropertiesResponse;
    private List<Client> clientResponse;
    private static final String USER_ID = "0123450";
    private static final String USER_ID_INCORRECT = "012350";
    private final ManualRestDocumentation restDocumentation = new ManualRestDocumentation("build/docs/generated-snippets");

    /**
     * Sets up.
     * @param method the method
     */
    @BeforeMethod
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
                        fieldWithPath("results.systemproperties.id").description("This is the Id for System Properties."),
                        fieldWithPath("results.systemproperties.lastUpdatedDate").description("This is the System last Updated Date.")
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
                        fieldWithPath("results.error.message").description("This is Error Message in case of any error."),
                        fieldWithPath("results.error.code").description("This is Error Code representing the error.")
                )));

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
                        fieldWithPath("results.clients[].clientId").description("This is the Id for Client to which user of userId:2343249202 belongs to."),
                        fieldWithPath("results.clients[].name").description("This is Name of the Client."),
                        fieldWithPath("results.clients[].budgetYear").description("This is Financial Year of the client."),
                        fieldWithPath("results.clients[].projects[]").description("This gives the list of projects under the Client."),
                        fieldWithPath("results.clients[].projects[].projectId").description("This is the Id of the project."),
                        fieldWithPath("results.clients[].projects[].clientId").description("This is the Id of the Client of the project."),
                        fieldWithPath("results.clients[].projects[].title").description("This is Name of the Project."),
                        fieldWithPath("results.clients[].projects[].startDate").description("This is Start Date of the Project."),
                        fieldWithPath("results.clients[].projects[].endDate").description("This is End Date of the Project."),
                        fieldWithPath("results.clients[].projects[].plannedBudget").description("This is the Planned budget of the Project."),
                        fieldWithPath("results.clients[].projects[].users").description("This is list of users working in the Project.")
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
                        fieldWithPath("results.error.message").description("This is Error Message in case of any error."),
                        fieldWithPath("results.error.code").description("This is Error Code representing the error.")
                )));
    }

    /**
     * Tear down.
     */
    @AfterMethod
    public void tearDown() {
        this.restDocumentation.afterTest();
    }

}
