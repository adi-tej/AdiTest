package com.andigital.andservice.repository;

import com.andigital.andservice.BaseTestNG;
import com.andigital.andservice.model.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Date;

import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertNull;

/**
 * Created by rakeshchoudhary on 5/25/17.
 */
public class UserRepositoryIntegrationTest extends BaseTestNG{

    @Autowired
    UserRepository userRepository;

    private User testUser;

    @BeforeClass
    public void setUp(){
        testUser = new User();
        testUser.setId("2344327887");
        testUser.setFirstName("Arya");
        testUser.setLastName("Stark");
        testUser.setBaseRate("100");
        testUser.setEmail("test@email.com");
        testUser.setRole("analyst");
        testUser.setActivityDate(new Date());
        userRepository.save(testUser);
    }
    /**
     * Gets the user by id happy path.
     *
     * @return the user by id happy path
     * @throws Exception the exception
     */
    @Test
    public void testGetUserByIdSuccessResponse() throws Exception {
        String userId = testUser.getId();
        User user = userRepository.findById(userId);
        assertNotNull(user);
    }

    /**
     * Gets the user by id sad path.
     *
     * @return the user by id sad path
     * @throws Exception the exception
     */
    @Test
    public void testGetUserByIdErrorResponse() throws Exception {
        String userId = "-91";
        User user = userRepository.findById(userId);
        assertNull(user);
    }

    /**
     * cleans up the test repo
     */
    @AfterClass
    public void cleanUp(){
        userRepository.deleteAll();
    }

}
