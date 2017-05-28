package com.andigital.andservice.services;

import com.andigital.andservice.domain.ClientsList;

import java.util.List;

/**
 * Service Layer for projects.
 */
public interface ProjectsService {

	/**
	 * Gets projects.
	 *
	 * @param userId the user id
	 * @return the projects
	 * @throws Exception the exception
	 */
	public List<ClientsList> getProjects(String userId) throws Exception;

}