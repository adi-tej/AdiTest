package com.andigital.andservice.services;

import com.andigital.andservice.model.domain.SystemProperties;
import com.andigital.andservice.repository.SystemPropertiesRepository;
import com.andigital.andservice.services.impl.SystemPropertiesServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * @author premsingh
 * Test cases for System properties service.
 */
public class SystemPropertiesServiceTest{

    private static final String SYSTEM_PROPERTIES_ID = "12786328";
    private static final Date updatedDate = new Date();
    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private SystemPropertiesServiceImpl systemPropertiesServiceimpl;

    @Mock
    private SystemPropertiesRepository systemPropertiesRepository;

    private List<SystemProperties> systemPropertiesReponse;
    /**
     * Sets up.
     * @throws Exception the exception
     */
    @BeforeClass
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        systemPropertiesReponse = Arrays.asList(new SystemProperties(SYSTEM_PROPERTIES_ID,updatedDate));
    }
    /**
     * Mock test get system propertie.
     * @throws Exception the exception
     */
    @Test
    public void testSystemPropertiesServiceSuccess() throws Exception {

        when(systemPropertiesRepository.findAll()).thenReturn(systemPropertiesReponse);
        List<SystemProperties> systemProperties = systemPropertiesServiceimpl.getSystemProperties();
        assertNotNull(systemProperties);
        assertNotNull(systemProperties.get(0).getId());
        assertNotNull(systemProperties.get(0).getLastUpdatedDate());
        assertEquals(systemProperties.get(0).getId(), SYSTEM_PROPERTIES_ID);
        assertEquals(systemProperties.get(0).getLastUpdatedDate(),updatedDate);
    }
}