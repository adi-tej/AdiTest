package com.andigital.andservice.controller;

import com.andigital.andservice.BaseTestNG;
import com.andigital.andservice.common.Constant;
import com.andigital.andservice.common.UserType;
import com.andigital.andservice.model.domain.Client;
import com.andigital.andservice.model.domain.Project;
import com.andigital.andservice.model.domain.SystemProperties;
import com.andigital.andservice.model.domain.User;
import com.andigital.andservice.repository.ProjectRepository;
import com.andigital.andservice.repository.SystemPropertiesRepository;
import com.andigital.andservice.repository.UserRepository;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.testng.Assert;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.testng.Assert.assertNotNull;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Rest Assured Integration Tests for the Dashboard Controller.
 */
@Test(groups = "controllers")
public class DashboardControllerIntegrationTest extends BaseTestNG {

	@LocalServerPort
	private int port;

	private static final String USER_ID_FOR_CONTRACTOR = "2343249203";
	private static final String USER_ID_FOR_CLIENT = "2343249202";
	private static final String USER_ID_FOR_EMPLOYEE = "2343249201";
	private static final String USER_ID_INCORRECT = "23432492";
	private static final String CLIENT_ID_1 = "0123450";
	private static final String CLIENT_ID_2 = "0678910";
	private static final String CLIENT_1 = "client1";
	private static final String CLIENT_2 = "client2";
	private static final String BUDGET_YEAR_JAN_DEC = "Jan-Dec";
	private static final String BUDGET_YEAR_APR_MAR = "Apr-Mar";
	private static final String PROJECT_1 = "project1";
	private static final String PROJECT_2 = "project2";
	private static final String USER = "user";
	private static final String PROJECT = "project";
	private static final String CLIENT = "client";
	private static final Date updatedDate = new Date("01/02/2017");

	@Autowired
	private MongoTemplate mongoTemplate;
	@Autowired
	private SystemPropertiesRepository systemPropertiesRepository;

	@Autowired
	private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;
    private Project testProject;
    private User testSDM;
    private User testUser1;
    private User testUser2;
    private List<User> testUsers;


    private void setupProjectAndTestUsers(){
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
	 * Sets up.
	 * @throws Exception the exception
	 */
	@BeforeGroups("controllers")
	public void setUp() throws Exception {
		//Inserting last_updated_date into system_properties collection
		SystemProperties sys = new SystemProperties();
		sys.setLastUpdatedDate(updatedDate);
		// save System Properties, verify has ID value after save
		Assert.assertNull(sys.getId());
		this.systemPropertiesRepository.save(sys);
		Assert.assertNotNull(sys.getId());

		//Inserting users with different type into user collection
		User user1 = getUserObject(CLIENT_ID_1,USER_ID_FOR_EMPLOYEE,UserType.EMPLOYEE.getType());
		DBCollection userCollection=mongoTemplate.getCollection(USER);
		DBObject userObject1 = new BasicDBObject();
		mongoTemplate.getConverter().write(user1,userObject1);
		User user2 = getUserObject(CLIENT_ID_2,USER_ID_FOR_CLIENT,UserType.CLIENT.getType());
		DBObject userObject2 = new BasicDBObject();
		mongoTemplate.getConverter().write(user2,userObject2);
		User user3 = getUserObject(CLIENT_ID_2,USER_ID_FOR_CONTRACTOR,UserType.CLIENT.getType());
		DBObject userObject3 = new BasicDBObject();
		mongoTemplate.getConverter().write(user3,userObject3);

		List<DBObject> dbUsersObject = Arrays.asList(userObject1,userObject2,userObject3);
		userCollection.insert(dbUsersObject);

		//Inserting data into project collection
		DBCollection projectCollection=mongoTemplate.getCollection(PROJECT);
		Project project1 = new Project(PROJECT_1,CLIENT_ID_1,new SimpleDateFormat(Constant.DATE_FORMAT_DD_MM_YYYY).parse("01/01/2017"),new SimpleDateFormat(Constant.DATE_FORMAT_DD_MM_YYYY).parse("12/02/2017"),null,null);
		DBObject projectObject1 = new BasicDBObject();
		mongoTemplate.getConverter().write(project1,projectObject1);
		Project project2 = new Project(PROJECT_2,CLIENT_ID_2,new SimpleDateFormat(Constant.DATE_FORMAT_DD_MM_YYYY).parse("01/01/2017"),new SimpleDateFormat(Constant.DATE_FORMAT_DD_MM_YYYY).parse("12/02/2017"),null,null);
		DBObject projectObject2 = new BasicDBObject();
		mongoTemplate.getConverter().write(project2,projectObject2);
		List<DBObject> dbProjectsObject = Arrays.asList(projectObject1,projectObject2);
		projectCollection.insert(dbProjectsObject);

		//Inserting data into client collection
		DBCollection clientCollection=mongoTemplate.getCollection(CLIENT);
		Client client1 = new Client(CLIENT_ID_1,CLIENT_1,BUDGET_YEAR_JAN_DEC,null);
		DBObject clientObject1 = new BasicDBObject();
		mongoTemplate.getConverter().write(client1,clientObject1);
		Client client2 = new Client(CLIENT_ID_2,CLIENT_2,BUDGET_YEAR_APR_MAR,null);
		DBObject clientObject2 = new BasicDBObject();
		mongoTemplate.getConverter().write(client2,clientObject2);
		List<DBObject> dbClientsObject = Arrays.asList(clientObject1,clientObject2);
		clientCollection.insert(dbClientsObject);
		//sets up test users and project
		setupProjectAndTestUsers();
	}
	/**
	 * Sample Test for Dashboard test API
	 * @throws Exception the exception
	 * Test dashboard api.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testDashboardApi() throws Exception {
		given().port(port).contentType(MediaType.TEXT_PLAIN_VALUE).when().get("/dashboard/test").then()
				.statusCode(200);
	}

    /**
     * Test get project by id happy path.
     */
    @Test
    public void testGetProjectByIdSuccessResponse() {
        Response res = given().port(port).contentType(MediaType.TEXT_PLAIN_VALUE).when().get("/dashboard/project/"+testProject.getId()).then()
                .statusCode(200).extract().response();
        assertNotNull(res);
        assertEquals(res.getBody().jsonPath().getString("status"), Constant.SUCCESS);
        assertNotNull(res.getBody().jsonPath().getString("dashboard.project"));
        assertNotNull(res.getBody().jsonPath().getString("dashboard.project.id"));
		assertNotNull(res.getBody().jsonPath().getString("dashboard.serviceDeliveryManager"));
	}
    /**
     * Test get project by id sad path.
     */
    @Test
    public void testGetProjectByIdErrorResponse() {
        Response res = given().port(port).contentType(MediaType.TEXT_PLAIN_VALUE).when().get("/dashboard/project/-11").then()
                .statusCode(200).extract().response();
        assertNotNull(res);
        assertEquals(res.getBody().jsonPath().getString("status"), Constant.ERROR);
        assertNotNull(res.getBody().jsonPath().getString("error.message"));
        assertNotNull(res.getBody().jsonPath().getString("error.message"));
        assertNotNull(res.getBody().jsonPath().getString("error.code"));
    }

	/*
	 * INTEGRATION TESTS FOR SYSTEM_PROPERTIES API ENDPOINT
	 */
	/**
	 * When a PUT call is made on System Properties API Endpoint
	 */
	@Test
	public void testGetSystemPropertiesWithPutMethod() {
		given().port(port).contentType(MediaType.APPLICATION_JSON_VALUE).
				when().put("/dashboard/systemproperties").
				then().statusCode(405);
	}

	/**
	 * When a POST call is made on System Properties API Endpoint
	 */
	@Test
	public void testGetSystemPropertiesWithPostMethod() {
		given().port(port).contentType(MediaType.APPLICATION_JSON_VALUE).
				when().put("/dashboard/systemproperties").
				then().statusCode(405);
	}

	/**
	 * When different content type is given
	 */
	@Test
	public void testGetSystemPropertiesForContentType() {
		given().port(port).contentType(MediaType.TEXT_XML_VALUE).
				when().get("/dashboard/systemproperties").
				then().statusCode(200);
	}


	/**
	 * System Properties API response success test.
	 */
	@Test
	public void testGetSystemPropertiesForSuccessResponse() throws ParseException {
		Response res = given().port(port).contentType(MediaType.APPLICATION_JSON_VALUE).
				when().get("/dashboard/systemproperties").
				then().contentType(ContentType.JSON).statusCode(200).body(containsString(Constant.SYSTEM_PROPERTIES)).extract().response();
		assertNotNull(res);
		assertEquals(res.getBody().jsonPath().getString("status"), Constant.SUCCESS);
        assertNotNull(res.getBody().jsonPath().getString("systemproperties.id"));
        assertNotNull(res.getBody().jsonPath().getString("systemproperties.lastUpdatedDate"));
		assertEquals(res.getBody().jsonPath().getString("systemproperties.lastUpdatedDate"),new SimpleDateFormat(Constant.DATE_FORMAT_DD_MM_YYYY).format(updatedDate));
		assertTrue(res.getBody().jsonPath().getString("systemproperties.lastUpdatedDate").length() > 0);
	}

	/**
	 * System Properties API response error test.
	 */
	@Test(dependsOnMethods={"testGetSystemPropertiesForSuccessResponse"})
	public void testGetSystemPropertiesForErrorResponse() {
		this.systemPropertiesRepository.deleteAll();
		Response res = given().port(port).contentType(MediaType.APPLICATION_JSON_VALUE).
				when().get("/dashboard/systemproperties").
				then().contentType(ContentType.JSON).statusCode(200).body(containsString(Constant.ERROR)).extract().response();
		assertNotNull(res);
		assertEquals(res.getBody().jsonPath().getString("status"),Constant.ERROR);
		assertNotNull(res.getBody().jsonPath().getString("error.message"));
		assertNotNull(res.getBody().jsonPath().getString("error.code"));
	}

	/*
	 * INTEGRATION TESTS FOR CLIENT_PROJECTS API ENDPOINT
	 */
	/**
	 * When a PUT call is made on Client Projects API Endpoint
	 */
	@Test
	public void testGetProjectsListWithPutMethod() {

		given().port(port).pathParam("userid", USER_ID_FOR_EMPLOYEE).contentType(MediaType.APPLICATION_JSON_VALUE).
				when().put("/dashboard/user/{userid}/projects").
				then().statusCode(405);
	}

	/**
	 * When a PUT call is made on Client Projects API Endpoint
	 */
	@Test
	public void testGetProjectsListWithPostMethod() {

		given().port(port).pathParam("userid", USER_ID_FOR_EMPLOYEE).contentType(MediaType.APPLICATION_JSON_VALUE).
				when().post("/dashboard/user/{userid}/projects").
				then().statusCode(405);
	}

	/**
	 * When different content type is given
	 */
	@Test
	public void testGetProjectsListForContentType() {
		given().port(port).pathParam("userid", USER_ID_FOR_EMPLOYEE).contentType(MediaType.TEXT_XML_VALUE).
				when().get("/dashboard/user/{userid}/projects").
				then().statusCode(200);
	}

	/**
	 * Client Projects API response success test.
	 * Case when userId of user type 'employee' is given
	 */
	@Test
	public void testGetProjectsListForUserTypeEmployee() {
		Response res = given().port(port).pathParam("userid", USER_ID_FOR_EMPLOYEE).contentType(MediaType.APPLICATION_JSON_VALUE).
				when().get("/dashboard/user/{userid}/projects").
				then().contentType(ContentType.JSON).statusCode(200).body(containsString(Constant.CLIENTS)).extract().response();
		assertNotNull(res);
		Assert.assertEquals(res.getBody().jsonPath().getString("status"), Constant.SUCCESS);
		Assert.assertTrue(res.getBody().jsonPath().getList("clients").size()>1);
		assertNotNull(res.getBody().jsonPath().getList("clientId").get(0));
		assertNotNull(res.getBody().jsonPath().getList("clients.name").get(0));
		assertNotNull(res.getBody().jsonPath().getList("clients.budgetYear").get(0));
		assertNotNull(res.getBody().jsonPath().getList("clients.projects.id").get(0));
		assertNotNull(res.getBody().jsonPath().getList("clients.projects.title").get(0));
		Assert.assertEquals(res.getBody().jsonPath().getList("clients.clientId").get(0),CLIENT_ID_1);
		Assert.assertEquals(res.getBody().jsonPath().getList("clients.clientId").get(1),CLIENT_ID_2);
		Assert.assertEquals(res.getBody().jsonPath().getList("clients.name").get(0),CLIENT_1);
		Assert.assertEquals(res.getBody().jsonPath().getList("clients.budgetYear").get(0),BUDGET_YEAR_JAN_DEC);
		Assert.assertEquals(res.getBody().jsonPath().getList("clients.projects.title").get(0), Arrays.asList(PROJECT_1));
	}
	/**
	 * Client Projects API response success test.
	 * Case when userId of user type 'client' is given
	 */
	@Test
	public void testGetProjectsListForUserTypeClient() {

		Response res = given().port(port).pathParam("userid",USER_ID_FOR_CLIENT).contentType(MediaType.APPLICATION_JSON_VALUE).
				when().get("/dashboard/user/{userid}/projects").
				then().contentType(ContentType.JSON).statusCode(200).body(containsString(Constant.CLIENTS)).extract().response();
		assertNotNull(res);
		Assert.assertEquals(res.getBody().jsonPath().getString("status"), Constant.SUCCESS);
		Assert.assertTrue(res.getBody().jsonPath().getList("clients").size()==1);
		assertNotNull(res.getBody().jsonPath().getList("clients.clientId"));
		assertNotNull(res.getBody().jsonPath().getList("clients.name"));
		assertNotNull(res.getBody().jsonPath().getList("clients.budgetYear"));
		assertNotNull(res.getBody().jsonPath().getList("clients.projects"));
		assertNotNull(res.getBody().jsonPath().getList("clients.projects.id"));
		assertNotNull(res.getBody().jsonPath().getList("clients.projects.title"));
		Assert.assertEquals(res.getBody().jsonPath().getList("clients.clientId").get(0),CLIENT_ID_2);
		Assert.assertEquals(res.getBody().jsonPath().getList("clients.name").get(0),CLIENT_2);
		Assert.assertEquals(res.getBody().jsonPath().getList("clients.budgetYear").get(0),BUDGET_YEAR_APR_MAR);
		Assert.assertEquals(res.getBody().jsonPath().getList("clients.projects.title").get(0), Arrays.asList(PROJECT_2));
	}

	/**
	 * Client Projects API response success test.
	 * Case when userId of user type 'contractor' is given
	 */
	@Test
	public void testGetProjectsListForUserTypeContractor() {

		Response res = given().port(port).pathParam("userid",USER_ID_FOR_CONTRACTOR).contentType(MediaType.APPLICATION_JSON_VALUE).
				when().get("/dashboard/user/{userid}/projects").
				then().contentType(ContentType.JSON).statusCode(200).body(containsString(Constant.CLIENTS)).extract().response();
		assertNotNull(res);
		Assert.assertEquals(res.getBody().jsonPath().getString("status"), Constant.SUCCESS);
		Assert.assertTrue(res.getBody().jsonPath().getList("clients").size()==1);
		assertNotNull(res.getBody().jsonPath().getList("clients.clientId"));
		assertNotNull(res.getBody().jsonPath().getList("clients.name"));
		assertNotNull(res.getBody().jsonPath().getList("clients.budgetYear"));
		assertNotNull(res.getBody().jsonPath().getList("clients.projects"));
		assertNotNull(res.getBody().jsonPath().getList("clients.projects.id"));
		assertNotNull(res.getBody().jsonPath().getList("clients.projects.title"));
		Assert.assertEquals(res.getBody().jsonPath().getList("clients.clientId").get(0),CLIENT_ID_2);
		Assert.assertEquals(res.getBody().jsonPath().getList("clients.name").get(0),CLIENT_2);
		Assert.assertEquals(res.getBody().jsonPath().getList("clients.budgetYear").get(0),BUDGET_YEAR_APR_MAR);
		Assert.assertEquals(res.getBody().jsonPath().getList("clients.projects.title").get(0), Arrays.asList(PROJECT_2));
	}

	/**
	 * Client Projects API response error tests.
	 * Case when unidentified userId is given
	 */
	@Test
	public void testGetProjectsListForErrorResponse() {
		Response res = given().port(port).pathParam("userid", USER_ID_INCORRECT).contentType(MediaType.APPLICATION_JSON_VALUE).
				when().get("/dashboard/user/{userid}/projects").
				then().contentType(ContentType.JSON).statusCode(200).body(containsString(Constant.ERROR)).extract().response();
		assertNotNull(res);
		Assert.assertEquals(res.getBody().jsonPath().getString("status"),Constant.ERROR);
		assertNotNull(res.getBody().jsonPath().getString("error.message"));
		assertNotNull(res.getBody().jsonPath().getString("error.code"));
	}

	/**
	 * Tear down.
	 * @throws Exception the exception
	 */
	@AfterGroups("controllers")
	public void tearDown() throws Exception {
		this.systemPropertiesRepository.deleteAll();
		mongoTemplate.dropCollection(USER);
		mongoTemplate.dropCollection(PROJECT);
		mongoTemplate.dropCollection(CLIENT);
	}
}