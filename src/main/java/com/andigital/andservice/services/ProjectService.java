package com.andigital.andservice.services;

import com.andigital.andservice.exception.ANDApplicationException;
import com.andigital.andservice.model.DashboardResponse;

/**
 * Created by rakeshchoudhary on 5/24/17.
 */
public interface ProjectService {

    /**
     *@projectId is the input param, this method gathers the data from User and Project repository and returns
     * accumulated data as response
     */
    DashboardResponse getProjectById(String projectId) throws ANDApplicationException;
}
