package com.andigital.andservice;

import com.andigital.andservice.model.domain.User;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 * @author premsingh
 * Base class for Integration Test cases.
 */
@ContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public abstract class BaseTestNG extends AbstractTestNGSpringContextTests {

    /**
     * Gets user object.
     * @param clientId the client id
     * @param userId   the user id
     * @param userType the user type
     * @return the user object
     */
    protected User getUserObject(String clientId, String userId, String userType) {
        User u = new User();
        u.setType(userType);
        u.setClientId(clientId);
        u.setUserId(userId);
        return u;
    }
}
