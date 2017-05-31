package com.andigital.andservice.repository;

import com.andigital.andservice.model.domain.Client;

import java.util.List;


/**
 * The interface Client repository.
 */
public interface ClientRepository {
    /**
     * Gets projects.
     *
     * @param userId the user id
     * @return the projects
     */
    List<Client> getClientProjectsByUserId(String userId);
}
