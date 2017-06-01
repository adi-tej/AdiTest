package com.andigital.andservice.repository;

import com.andigital.andservice.model.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by rakeshchoudhary on 5/24/17.
 */
public interface UserRepository extends MongoRepository<User, String> {
    
    /**
     * Find by user id.
     *
     * @param id the user id
     * @return the user
     */
    User findById(String id) throws Exception;
};

