package com.andigital.andservice.services;

import com.andigital.andservice.common.Constant;
import com.andigital.andservice.exception.ANDApplicationException;
import com.andigital.andservice.model.domain.Client;
import com.andigital.andservice.model.domain.Project;
import com.andigital.andservice.repository.ClientRepository;
import com.andigital.andservice.services.impl.ClientServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

/**
 * Test class for client service
 */
public class ClientServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private ClientServiceImpl clientService;

    @Mock
    private ClientRepository clientRepository;

    private List<Client> clientResponse;
    private static final String USER_ID = "0123450";
    private static final String CLIENT_ID = "12345";
    private static final String CLIENT_NAME = "clientName";
    private static final String BUDGET_YEAR_JAN_DEC = "Jan-Dec";
    private static final String PROJECT_TITLE = "projectTitle";
    private static final String START_DATE = "02/23/2017";
    private static final String END_DATE = "03/23/2017";
    /**
     * Sets up.
     * @throws Exception the exception
     */
    @BeforeClass
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        clientResponse = Arrays.asList(new Client(CLIENT_ID, CLIENT_NAME, BUDGET_YEAR_JAN_DEC, Arrays.asList(new Project(PROJECT_TITLE,null, new SimpleDateFormat(Constant.DATE_FORMAT_DD_MM_YYYY).parse(START_DATE), new SimpleDateFormat(Constant.DATE_FORMAT_DD_MM_YYYY).parse(END_DATE),null,null))));
    }

    /**
     * Test client service success.
     * @throws Exception the exception
     */
    @Test
    public void testClientProjectsByUserIdSuccess() throws Exception {

        when(clientRepository.getClientProjectsByUserId(USER_ID)).thenReturn(clientResponse);
        List<Client> clientProjects = clientService.getClientProjectsByUserId(USER_ID);
        assertNotNull(clientProjects);
        assertNotNull(clientProjects.get(0).getClientId());
        assertNotNull(clientProjects.get(0).getName());
        assertNotNull(clientProjects.get(0).getBudgetYear());
        assertNotNull(clientProjects.get(0).getProjects().get(0).getTitle());
        assertNotNull(clientProjects.get(0).getProjects().get(0).getStartDate());
        assertNotNull(clientProjects.get(0).getProjects().get(0).getEndDate());
        assertEquals(clientProjects.get(0).getClientId(), CLIENT_ID);
        assertEquals(clientProjects.get(0).getName(), CLIENT_NAME);
        assertEquals(clientProjects.get(0).getBudgetYear(),BUDGET_YEAR_JAN_DEC);
        assertEquals(clientProjects.get(0).getProjects().get(0).getTitle(),PROJECT_TITLE);
        assertEquals(clientProjects.get(0).getProjects().get(0).getStartDate(),new SimpleDateFormat(Constant.DATE_FORMAT_DD_MM_YYYY).parse(START_DATE));
        assertEquals(clientProjects.get(0).getProjects().get(0).getEndDate(),new SimpleDateFormat(Constant.DATE_FORMAT_DD_MM_YYYY).parse(END_DATE));
    }

    /**
     * Test client service throws ANDApplicationException.
     * @throws Exception the exception
     */
    @Test(expectedExceptions = ANDApplicationException.class)
    public void testClientProjectsByUserIdWithException() throws Exception {

        when(clientRepository.getClientProjectsByUserId(USER_ID)).thenThrow(new RuntimeException());
        List<Client> clientProjects = clientService.getClientProjectsByUserId(USER_ID);
        assertNotNull(clientProjects);
    }
    /**
     * Test client service failure.
     * @throws Exception the exception
     */
    @Test
    public void testClientProjectsByUserIdFailure() throws Exception {
        List<Client> clientProjects = clientService.getClientProjectsByUserId(null);
        assertTrue(clientProjects.isEmpty());
    }
}
