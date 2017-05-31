package com.andigital.andservice.controller;

import com.andigital.andservice.BaseTestNG;
import com.andigital.andservice.common.Constant;
import com.andigital.andservice.common.UserType;
import com.andigital.andservice.model.domain.Client;
import com.andigital.andservice.model.domain.Project;
import com.andigital.andservice.model.domain.SystemProperties;
import com.andigital.andservice.model.domain.User;
import com.andigital.andservice.repository.SystemPropertiesRepository;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        assertNotNull(res.getBody().jsonPath().getString("results.systemproperties.id"));
        assertNotNull(res.getBody().jsonPath().getString("results.systemproperties.lastUpdatedDate"));
		assertEquals(res.getBody().jsonPath().getString("results.systemproperties.lastUpdatedDate"),new SimpleDateFormat(Constant.DATE_FORMAT_DD_MM_YYYY).format(updatedDate));
		assertTrue(res.getBody().jsonPath().getString("results.systemproperties.lastUpdatedDate").length() > 0);
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
		assertNotNull(res.getBody().jsonPath().getString("results.error.message"));
		assertNotNull(res.getBody().jsonPath().getString("results.error.code"));
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
		Assert.assertTrue(res.getBody().jsonPath().getList("results.clients").size()>1);
		assertNotNull(res.getBody().jsonPath().getList("results.clients.clientId").get(0));
		assertNotNull(res.getBody().jsonPath().getList("results.clients.name").get(0));
		assertNotNull(res.getBody().jsonPath().getList("results.clients.budgetYear").get(0));
		assertNotNull(res.getBody().jsonPath().getList("results.clients.projects.id").get(0));
		assertNotNull(res.getBody().jsonPath().getList("results.clients.projects.title").get(0));
		Assert.assertEquals(res.getBody().jsonPath().getList("results.clients.clientId").get(0),CLIENT_ID_1);
		Assert.assertEquals(res.getBody().jsonPath().getList("results.clients.clientId").get(1),CLIENT_ID_2);
		Assert.assertEquals(res.getBody().jsonPath().getList("results.clients.name").get(0),CLIENT_1);
		Assert.assertEquals(res.getBody().jsonPath().getList("results.clients.budgetYear").get(0),BUDGET_YEAR_JAN_DEC);
		Assert.assertEquals(res.getBody().jsonPath().getList("results.clients.projects.title").get(0), Arrays.asList(PROJECT_1));
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
		Assert.assertTrue(res.getBody().jsonPath().getList("results.clients").size()==1);
		assertNotNull(res.getBody().jsonPath().getList("results.clients.clientId"));
		assertNotNull(res.getBody().jsonPath().getList("results.clients.name"));
		assertNotNull(res.getBody().jsonPath().getList("results.clients.budgetYear"));
		assertNotNull(res.getBody().jsonPath().getList("results.clients.projects"));
		assertNotNull(res.getBody().jsonPath().getList("results.clients.projects.id"));
		assertNotNull(res.getBody().jsonPath().getList("results.clients.projects.title"));
		Assert.assertEquals(res.getBody().jsonPath().getList("results.clients.clientId").get(0),CLIENT_ID_2);
		Assert.assertEquals(res.getBody().jsonPath().getList("results.clients.name").get(0),CLIENT_2);
		Assert.assertEquals(res.getBody().jsonPath().getList("results.clients.budgetYear").get(0),BUDGET_YEAR_APR_MAR);
		Assert.assertEquals(res.getBody().jsonPath().getList("results.clients.projects.title").get(0), Arrays.asList(PROJECT_2));
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
		Assert.assertTrue(res.getBody().jsonPath().getList("results.clients").size()==1);
		assertNotNull(res.getBody().jsonPath().getList("results.clients.clientId"));
		assertNotNull(res.getBody().jsonPath().getList("results.clients.name"));
		assertNotNull(res.getBody().jsonPath().getList("results.clients.budgetYear"));
		assertNotNull(res.getBody().jsonPath().getList("results.clients.projects"));
		assertNotNull(res.getBody().jsonPath().getList("results.clients.projects.id"));
		assertNotNull(res.getBody().jsonPath().getList("results.clients.projects.title"));
		Assert.assertEquals(res.getBody().jsonPath().getList("results.clients.clientId").get(0),CLIENT_ID_2);
		Assert.assertEquals(res.getBody().jsonPath().getList("results.clients.name").get(0),CLIENT_2);
		Assert.assertEquals(res.getBody().jsonPath().getList("results.clients.budgetYear").get(0),BUDGET_YEAR_APR_MAR);
		Assert.assertEquals(res.getBody().jsonPath().getList("results.clients.projects.title").get(0), Arrays.asList(PROJECT_2));
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
		assertNotNull(res.getBody().jsonPath().getString("results.error.message"));
		assertNotNull(res.getBody().jsonPath().getString("results.error.code"));
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