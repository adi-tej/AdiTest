package com.andigital.andservice.domain;

import java.util.List;

/**
 * Created by aditeja on 5/24/2017.
 */
public class ProjectsList {

    private String clientId;
    private List<String> projects;

    /**
     * Gets projects.
     *
     * @return Value of projects.
     */
    public List<String> getProjects() {
        return projects;
    }

    /**
     * Sets new clientId.
     *
     * @param clientId New value of clientId.
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * Sets new projects.
     *
     * @param projects New value of projects.
     */
    public void setProjects(List<String> projects) {
        this.projects = projects;
    }

    /**
     * Gets clientId.
     *
     * @return Value of clientId.
     */
    public String getClientId() {
        return clientId;
    }

    @Override
    public String toString() {
        return "ProjectsList{" +
                "clientId='" + clientId + '\'' +
                ", projects=" + projects +
                '}';
    }
}
