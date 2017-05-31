package com.andigital.andservice.services;

import com.andigital.andservice.model.domain.SystemProperties;

import java.util.List;

/**
 * @author premsingh
 * Service Layer for System properties
 */
public interface SystemPropertiesService {
	
	/**
	 * API to return list of System properties
	 * @return system properties
	 * @throws Exception the exception
	 */
	public List<SystemProperties> getSystemProperties() throws Exception;
}
