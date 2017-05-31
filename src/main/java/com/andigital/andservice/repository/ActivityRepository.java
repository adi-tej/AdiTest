package com.andigital.andservice.repository;

import com.andigital.andservice.model.domain.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by rakeshchoudhary on 5/24/17.
 */
public interface ActivityRepository extends MongoRepository<Activity,String> {
    // to include methods later
}
