package com.andigital.andservice.repository;

import com.andigital.andservice.domain.ClientsList;

import java.util.List;

/**
 * The interface Projects repository.
 */
public interface ProjectsRepository {

	/**
	 * Gets projects.
	 *
	 * @param userId the user id
	 * @return the projects
	 */
	List<ClientsList> getProjects(String userId);
}
