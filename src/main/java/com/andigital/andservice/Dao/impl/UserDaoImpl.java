package com.andigital.andservice.Dao.impl;

import com.andigital.andservice.Dao.UserDao;
import com.andigital.andservice.model.domain.User;
import com.andigital.andservice.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by rakeshchoudhary on 5/24/17.
 */
@Service
public class UserDaoImpl implements UserDao {

    @Autowired
    UserRepository userRepository;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     *  @userId is the unique id of a user. 
     *  This method looks up the userDao and returns identified data.
     */
    @Override
    public User findUserByUserId(String userId) throws Exception {
        logger.debug("Getting project details: {}",userId);
        return userRepository.findByUserId(userId);
    }
}
