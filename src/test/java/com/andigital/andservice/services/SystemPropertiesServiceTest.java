package com.andigital.andservice.services;

import com.andigital.andservice.domain.SystemProperties;
import com.andigital.andservice.repository.SystemPropertiesRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * @author premsingh
 * Test cases for System properties service.
 */
public class SystemPropertiesServiceTest{

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private SystemPropertiesServiceImpl systemPropertiesServiceimpl;

    @Mock
    private SystemPropertiesRepository systemPropertiesRepository;


    /**
     * Sets up.
     * @throws Exception the exception
     */
    @BeforeClass
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
    /**
     * Mock test get system propertie.
     * @throws Exception the exception
     */
    @Test
    public void testSystemPropertiesServiceSuccess() throws Exception {

        when(systemPropertiesRepository.findAll()).thenReturn(Arrays.asList(new SystemProperties("12786328","27-05-2017")));
        List<SystemProperties> systemProperties = systemPropertiesServiceimpl.getSystemProperties();
        assertNotNull(systemProperties);
        assertNotNull(systemProperties.get(0).getId());
        assertNotNull(systemProperties.get(0).getLastUpdatedDate());
        assertEquals(systemProperties.get(0).getId(),"12786328");
        assertEquals(systemProperties.get(0).getLastUpdatedDate(),"27-05-2017");
    }

    /**
     * Tear down.
     * @throws Exception the exception
     */
    @AfterMethod
    public void tearDown() throws Exception {
    }
}