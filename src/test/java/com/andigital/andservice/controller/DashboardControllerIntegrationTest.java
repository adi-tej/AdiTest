package com.andigital.andservice.controller;

import com.andigital.andservice.BaseTestNG;
import com.andigital.andservice.common.Constant;
import com.andigital.andservice.domain.SystemProperties;
import com.andigital.andservice.repository.SystemPropertiesRepository;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.http.MediaType;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Date;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

/**
 * Rest Assured Integration Test class
 *
 */
@Test(groups = "controllers")
public class DashboardControllerIntegrationTest extends BaseTestNG {

	@LocalServerPort
	private int port;

	@Autowired
	private SystemPropertiesRepository systemPropertiesRepository;

	private static String updatedDate = new Date().toString();

	/**
	 * Sets up.
	 * @throws Exception the exception
	 */
	@BeforeMethod
	public void setUp() throws Exception {
		SystemProperties sys = new SystemProperties();
		sys.setLastUpdatedDate(updatedDate);
		// save System Properties, verify has ID value after save
		Assert.assertNull(sys.getId());
		this.systemPropertiesRepository.save(sys);
		Assert.assertNotNull(sys.getId());
	}

	/**
	 * Sample Test for Dashboard test API
	 * @throws Exception the exception
	 */
	@Test
	public void testDashboardApi() throws Exception {
		given().port(port).contentType(MediaType.TEXT_PLAIN_VALUE).when().get("/dashboard/test").then()
				.statusCode(200);
	}

	/**
	 * When a PUT call is made on System Properties API URI
	 */
	@Test
	public void putTestNegative() {
		given().port(port).contentType(MediaType.APPLICATION_JSON_VALUE).
				when().put("/dashboard/systemproperties").
				then().statusCode(405);
	}

	/**
	 * When a POST call is made on System Properties API URI
	 */
	@Test
	public void postTestNegative() {
		given().port(port).contentType(MediaType.APPLICATION_JSON_VALUE).
				when().put("/dashboard/systemproperties").
				then().statusCode(405);
	}

	/**
	 * When different content type is given
	 */
	@Test
	public void contentTypePositive() {
		given().port(port).contentType(MediaType.TEXT_XML_VALUE).
				when().get("/dashboard/systemproperties").
				then().statusCode(200);
	}


	/**
	 * Last updated date response success test.
	 */
	@Test
	public void lastUpdatedDateResponseSuccessTest() {

		Response res = given().port(port).contentType(MediaType.APPLICATION_JSON_VALUE).
				when().get("/dashboard/systemproperties").
				then().contentType(ContentType.JSON).statusCode(200).body(containsString("systemproperties")).extract().response();
		assertNotNull(res);
		assertEquals(res.getBody().jsonPath().getString("status"), Constant.SUCCESS);
        assertNotNull(res.getBody().jsonPath().getString("results.systemproperties.id"));
        assertNotNull(res.getBody().jsonPath().getString("results.systemproperties.lastUpdatedDate"));
		assertEquals(res.getBody().jsonPath().getJsonObject("results.systemproperties.lastUpdatedDate"),updatedDate);
		assertTrue(res.getBody().jsonPath().getString("results.systemproperties.lastUpdatedDate").length() > 0);
	}

	/**
	 * Last updated date response error test.
	 */
	@Test
	public void lastUpdatedDateResponseErrorTest() {

		this.systemPropertiesRepository.deleteAll();
		Response res = given().port(port).contentType(MediaType.APPLICATION_JSON_VALUE).
				when().get("/dashboard/systemproperties").
				then().contentType(ContentType.JSON).statusCode(200).body(containsString("error")).extract().response();
		assertNotNull(res);
		assertEquals(res.getBody().jsonPath().getString("status"),Constant.ERROR);
		assertNotNull(res.getBody().jsonPath().getString("results.error.message"));
		assertNotNull(res.getBody().jsonPath().getString("results.error.code"));
	}
}
