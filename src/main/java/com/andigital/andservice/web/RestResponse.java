package com.andigital.andservice.web;

import java.util.HashMap;
import java.util.Map;

/**
 * This will be returned as response of all ANDService API endpoints
 *
 * @param <T> the type parameter
 */
public class RestResponse<T> {
    private String status;
    private Map results;

    /**
     * Instantiates a new Rest response.
     */
    public RestResponse(){
        /*
         * Default Constructor
         */
    }

    /**
     * Instantiates a new Rest response.
     * @param status  the status
     * @param name   the name
     * @param results the results
     */
    public RestResponse(String status, String name, T results){
        this.status=status;
        Map<String,T> map = new HashMap<>();
        map.put(name,results);
        this.results = map;
    }

    /**
     * Instantiates a new Rest response.
     * @param status  the status
     * @param results the results
     */
    public RestResponse(String status, T results){
        this.status=status;
        Map<String,T> map = new HashMap<>();
        map.put(status,results);
        this.results = map;
    }

    /**
     * Gets status.
     * @return the status
     */
    public String getstatus() {
        return status;
    }

    /**
     * Sets status.
     * @param status the status to set
     */
    public void setstatus(String status) {
        this.status = status;
    }

    /**
     * Gets results.
     * @return the results
     */
    public Map getresults() {
        return results;
    }

    /**
     * Sets results.
     * @param results the results to set
     */
    public void setresults(Map results) {
        this.results = results;
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RestResponse{");
        sb.append("status=").append(status);
        sb.append(", results=").append(results);
        sb.append('}');
        return sb.toString();
    }
}