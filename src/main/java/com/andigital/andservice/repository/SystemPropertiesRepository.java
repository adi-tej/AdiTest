package com.andigital.andservice.repository;

import com.andigital.andservice.domain.SystemProperties;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * The interface System properties repository.
 * @author aditeja
 */
public interface SystemPropertiesRepository extends MongoRepository<SystemProperties, String> {}