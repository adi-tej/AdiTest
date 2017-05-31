package com.andigital.andservice.repository;

import com.andigital.andservice.BaseTestNG;
import com.andigital.andservice.common.Constant;
import com.andigital.andservice.common.UserType;
import com.andigital.andservice.model.domain.Client;
import com.andigital.andservice.model.domain.Project;
import com.andigital.andservice.model.domain.User;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

/**
 * Test class for Client Repository
 */
@Test(groups = "client-projects")
public class ClientRepositoryIntegrationTest extends BaseTestNG{

    private static final String USER_ID_FOR_CONTRACTOR = "2343249203";
    private static final String USER_ID_FOR_CLIENT = "2343249202";
    private static final String USER_ID_FOR_EMPLOYEE = "2343249201";
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

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ClientRepository clientRepository;

    /**
     * Sets up.
     * @throws Exception the exception
     */
    @BeforeGroups("client-projects")
    public void setUp() throws Exception {
        //Inserting users with different type into user collection
        DBCollection userCollection=mongoTemplate.getCollection(USER);
        User user1 = getUserObject(CLIENT_ID_1,USER_ID_FOR_EMPLOYEE,UserType.EMPLOYEE.getType());
        DBObject userObject1 = new BasicDBObject();
        mongoTemplate.getConverter().write(user1,userObject1);
        User user2 = getUserObject(CLIENT_ID_2,USER_ID_FOR_CLIENT,UserType.CLIENT.getType());
        DBObject userObject2 = new BasicDBObject();
        mongoTemplate.getConverter().write(user2,userObject2);
        User user3 = getUserObject(CLIENT_ID_2,USER_ID_FOR_CONTRACTOR,UserType.CONTRACTOR.getType());
        DBObject userObject3 = new BasicDBObject();
        mongoTemplate.getConverter().write(user3,userObject3);

        List<DBObject> dbUsersObject = Arrays.asList(userObject1,userObject2,userObject3);
        userCollection.insert(dbUsersObject);

        //Inserting data into project collection
        DBCollection projectCollection=mongoTemplate.getCollection(PROJECT);
        Project project1 = new Project(PROJECT_1, CLIENT_ID_1,new SimpleDateFormat(Constant.DATE_FORMAT_DD_MM_YYYY).parse("01/01/2017"),new SimpleDateFormat(Constant.DATE_FORMAT_DD_MM_YYYY).parse("02/12/2017"),null,null);
        DBObject projectObject1 = new BasicDBObject();
        mongoTemplate.getConverter().write(project1,projectObject1);
        Project project2 = new Project(PROJECT_2, CLIENT_ID_2,new SimpleDateFormat(Constant.DATE_FORMAT_DD_MM_YYYY).parse("01/01/2017"),new SimpleDateFormat(Constant.DATE_FORMAT_DD_MM_YYYY).parse("02/12/2017"),null,null);
        DBObject projectObject2 = new BasicDBObject();
        mongoTemplate.getConverter().write(project2,projectObject2);
        List<DBObject> dbProjectsObject = Arrays.asList(projectObject1,projectObject2);
        projectCollection.insert(dbProjectsObject);

        //Inserting data into client collection
        DBCollection clientCollection=mongoTemplate.getCollection(CLIENT);
        Client client1 = new Client(CLIENT_ID_1, CLIENT_1, BUDGET_YEAR_JAN_DEC,null);
        DBObject clientObject1 = new BasicDBObject();
        mongoTemplate.getConverter().write(client1,clientObject1);
        Client client2 = new Client(CLIENT_ID_2, CLIENT_2, BUDGET_YEAR_APR_MAR, null);
        DBObject clientObject2 = new BasicDBObject();
        mongoTemplate.getConverter().write(client2,clientObject2);
        List<DBObject> dbClientsObject = Arrays.asList(clientObject1,clientObject2);
        clientCollection.insert(dbClientsObject);
    }

    /**
     * Test get client projects by user id for user type employee.
     */
    @Test
    public void testGetClientProjectsByUserIdUserTypeEmployee() {
        List<Client> clientList= clientRepository.getClientProjectsByUserId(USER_ID_FOR_EMPLOYEE);
        assertNotNull(clientList);
        assertTrue(clientList.size()>1);
        Client client=clientList.get(0);
        assertNotNull(client);
        assertNotNull(client.getClientId());
        assertNotNull(client.getName());
        assertNotNull(client.getBudgetYear());
        assertNotNull(client.getProjects());
        assertNotNull(client.getProjects().get(0).getProjectId());
        assertNotNull(client.getProjects().get(0).getTitle());
        assertNotNull(client.getProjects().get(0).getStartDate());
        assertNotNull(client.getProjects().get(0).getEndDate());
        assertEquals(client.getClientId(), CLIENT_ID_1);
        assertEquals(client.getName(), CLIENT_1);
        assertEquals(client.getBudgetYear(), BUDGET_YEAR_JAN_DEC);
        assertEquals(client.getProjects().get(0).getTitle(), PROJECT_1);
    }

    /**
     * Test get client projects by user id for user type client.
     */
    @Test
    public void testGetClientProjectsByUserIdUserTypeClient() {
        List<Client> clientList= clientRepository.getClientProjectsByUserId(USER_ID_FOR_CLIENT);
        assertNotNull(clientList);
        assertTrue(clientList.size()==1);
        Client client=clientList.get(0);
        assertNotNull(client);
        assertNotNull(client.getClientId());
        assertNotNull(client.getName());
        assertNotNull(client.getBudgetYear());
        assertNotNull(client.getProjects());
        assertNotNull(client.getProjects().get(0).getProjectId());
        assertNotNull(client.getProjects().get(0).getTitle());
        assertNotNull(client.getProjects().get(0).getStartDate());
        assertNotNull(client.getProjects().get(0).getEndDate());
        assertEquals(client.getClientId(), CLIENT_ID_2);
        assertEquals(client.getName(), CLIENT_2);
        assertEquals(client.getBudgetYear(), BUDGET_YEAR_APR_MAR);
        assertEquals(client.getProjects().get(0).getTitle(), PROJECT_2);
    }

    /**
     * Test get client projects by user id for user type contractor.
     */
    @Test
    public void testGetClientProjectsByUserIdUserTypeContractor() {
        List<Client> clientList= clientRepository.getClientProjectsByUserId(USER_ID_FOR_CONTRACTOR);
        assertNotNull(clientList);
        assertTrue(clientList.size()==1);
        Client client=clientList.get(0);
        assertNotNull(client);
        assertNotNull(client.getClientId());
        assertNotNull(client.getName());
        assertNotNull(client.getBudgetYear());
        assertNotNull(client.getProjects());
        assertNotNull(client.getProjects().get(0).getProjectId());
        assertNotNull(client.getProjects().get(0).getTitle());
        assertNotNull(client.getProjects().get(0).getStartDate());
        assertNotNull(client.getProjects().get(0).getEndDate());
        assertEquals(client.getClientId(), CLIENT_ID_2);
        assertEquals(client.getName(), CLIENT_2);
        assertEquals(client.getBudgetYear(), BUDGET_YEAR_APR_MAR);
        assertEquals(client.getProjects().get(0).getTitle(), PROJECT_2);
    }

    /**
     * Tear down.
     * @throws Exception the exception
     */
    @AfterGroups("client-projects")
    public void tearDown() throws Exception {
        mongoTemplate.dropCollection(USER);
        mongoTemplate.dropCollection(PROJECT);
        mongoTemplate.dropCollection(CLIENT);
    }
}
