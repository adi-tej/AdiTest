package com.andigital.andservice.Dao;

import com.andigital.andservice.model.domain.User;

/**
 * Created by rakeshchoudhary on 5/24/17.
 */
public interface UserDao {

    /**
     * @userId is the unique id of a user. This method lookups the userDao 
     * and returns identified data.
     */
    User findUserByUserId(String userId) throws Exception;

}
