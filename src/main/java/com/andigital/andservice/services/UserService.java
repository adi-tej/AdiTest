package com.andigital.andservice.services;

import com.andigital.andservice.exception.ANDApplicationException;
import com.andigital.andservice.model.domain.User;

/**
 * Created by rakeshchoudhary on 5/24/17.
 */
public interface UserService {

    /**
     * @userId is the unique id of a user. This method looks up the user repository and returns identified data.
     */
    User getUserByUserId(String userId) throws ANDApplicationException;

}
