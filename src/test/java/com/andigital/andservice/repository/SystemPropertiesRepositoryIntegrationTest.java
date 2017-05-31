package com.andigital.andservice.repository;

import com.andigital.andservice.BaseTestNG;
import com.andigital.andservice.model.domain.SystemProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;

/**
 * @author premsingh
 * Test class for SystemPropertiesRepository.
 */
@Test(groups="system-properties")
public class SystemPropertiesRepositoryIntegrationTest extends BaseTestNG {

	@Autowired
	private SystemPropertiesRepository systemPropertiesRepository;
	
	private static Date testDate = new Date("01/01/2017");

	/**
	 * Sets up.
	 *
	 * @throws Exception the exception
	 */



	@BeforeGroups("system-properties")
	public void setUp() throws Exception {
		SystemProperties sys = new SystemProperties();
		sys.setLastUpdatedDate(testDate);
		// save System Properties, verify has ID value after save
		Assert.assertNull(sys.getId());
		this.systemPropertiesRepository.save(sys);
		Assert.assertNotNull(sys.getId());
	}

	/**
	 * Test get system last updated date.
	 */
	@Test
	public void testGetSystemLastUpdatedDate() {
		/* Test data retrieval */
		List<SystemProperties> sysList = systemPropertiesRepository.findAll();
		Assert.assertNotNull(sysList);
		Assert.assertNotNull(sysList.get(0).getLastUpdatedDate());
		Assert.assertEquals(sysList.get(0).getLastUpdatedDate(), testDate);
	}

	/**
	 * Tear down.
	 *
	 * @throws Exception the exception
	 */
	@AfterGroups("system-properties")
	public void tearDown() throws Exception {
		this.systemPropertiesRepository.deleteAll();
	}

}
