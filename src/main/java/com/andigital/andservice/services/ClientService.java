package com.andigital.andservice.services;

import com.andigital.andservice.exception.ANDApplicationException;
import com.andigital.andservice.model.domain.Client;

import java.util.List;

/**
 * Service layer for client projects
 */
public interface ClientService{
    /**
     * Gets the list of Client and Projects for given user id.
     * If user is of type Client or Contractor, all projects of the user assigned client will be returned.
     * If user is of type Employee all projects of all clients will be returned.
     * @param userId the user id
     * @return the projects
     * @throws ANDApplicationException the exception
     */
    public List<Client> getClientProjectsByUserId(String userId) throws ANDApplicationException;
}
