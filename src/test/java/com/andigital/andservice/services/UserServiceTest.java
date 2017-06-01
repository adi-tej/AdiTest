package com.andigital.andservice.services;

import com.andigital.andservice.exception.ANDApplicationException;
import com.andigital.andservice.model.domain.User;
import com.andigital.andservice.repository.UserRepository;
import com.andigital.andservice.services.impl.UserServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Date;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

/**
 * Created by rakeshchoudhary on 5/25/17.
 */
public class UserServiceTest {

    @InjectMocks
    private UserService userService= new UserServiceImpl();

    @Mock
    private UserRepository userRepository;

    private User testUser;

    /**
     * Inits the mocks.
     */
    @BeforeMethod
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
        testUser = new User();
        testUser.setEmail("test@email.com");
        testUser.setRole("analyst");
        testUser.setActivityDate(new Date());
    }

    /**
     * Test find user by id.
     */
    @Test
    public void testFindUserByIdSuccessResponse() throws Exception {
        when(userRepository.findById("1")).thenReturn(testUser);
        User user = userService.getUserByUserId("1");
        verify(userRepository,atMost(1)).findById("1");
        assertEquals(user.getActivityDate(), this.testUser.getActivityDate());
    }

    @Test(expectedExceptions = {ANDApplicationException.class})
    public void testFindUserByIdExceptionResponse() throws Exception{
        Mockito.doThrow(new ANDApplicationException()).when(userRepository).findById("1");
        userService.getUserByUserId("1");
    }
}
