package com.andigital.andservice.services;

import com.andigital.andservice.BaseTestNG;
import com.andigital.andservice.model.domain.User;
import com.andigital.andservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Date;

import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertNull;

/**
 * Created by rakeshchoudhary on 5/27/17.
 */
public class UserServiceIntegrationTest extends BaseTestNG{
    
    @Autowired
    UserService userService;

    @Autowired
    private UserRepository userRepository;

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
        System.out.println(userId);
        User user = userService.getUserByUserId(userId);
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
        User user = userService.getUserByUserId(userId);
        assertNull(user);
    }

    @AfterClass
    public void cleanup() throws Exception{
        userRepository.deleteAll();
    }
}
