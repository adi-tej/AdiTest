package com.andigital.andservice.services.impl;

import com.andigital.andservice.exception.ANDApplicationException;
import com.andigital.andservice.model.domain.User;
import com.andigital.andservice.repository.UserRepository;
import com.andigital.andservice.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by rakeshchoudhary on 5/24/17.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * @userId is the unique id of a user. This method looks up the user repository and returns identified data.
     */
    @Override
    public User getUserByUserId(String userId) throws ANDApplicationException{
        logger.debug("Getting user details: {}", userId);
        User user = null;
        try {
            user = userRepository.findById(userId);
        } catch (Exception ex) {
            logger.error("An exception was thrown in UserServiceImpl {}",ex);
            throw new ANDApplicationException("UserRepository failed with an exception");
        }
        return user;
    }

}
