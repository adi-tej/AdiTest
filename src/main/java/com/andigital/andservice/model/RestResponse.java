package com.andigital.andservice.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This will be returned as response of all ANDService API endpoints
 */
public class RestResponse {
    private Map<String,Object> results = new LinkedHashMap<>();

    /**
     * @param key   the key
     * @param value the value
     */
    @JsonAnySetter
    public void add(String key, Object value) {
        results.put(key, value);
    }

    /**
     * Gets resultant map
     * @return the results
     */
    @JsonAnyGetter
    public Map<String,Object> getResults() {
        return results;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RestResponse{");
        sb.append(", results=").append(results);
        sb.append('}');
        return sb.toString();
    }

}