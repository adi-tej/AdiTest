package com.andigital.andservice.model.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * Created by aditeja on 5/29/2017.
 */
@Document(collection = "client")
public class Client {

    @Field("Id")
    private String clientId;

    @Field("name")
    private String name;

    @Field("budget_year")
    private String budgetYear;

    @Field("projects")
    private List<Project> projects;

    /**
     * Instantiates a new Client.
     *
     * @param clientId   the client id
     * @param name       the name
     * @param budgetYear the budget year
     * @param projects   the projects
     */
    public Client(String clientId, String name, String budgetYear, List<Project> projects) {
        this.clientId = clientId;
        this.name = name;
        this.budgetYear = budgetYear;
        this.projects = projects;
    }

    /**
     * Gets name.
     * @return Value of name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets new name.
     * @param name New value of name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets budgetYear.
     * @return Value of budgetYear.
     */
    public String getBudgetYear() {
        return budgetYear;
    }

    /**
     * Sets new budgetYear.
     * @param budgetYear New value of budgetYear.
     */
    public void setBudgetYear(String budgetYear) {
        this.budgetYear = budgetYear;
    }

    /**
     * Gets clientId.
     * @return Value of clientId.
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * Sets new clientId.
     * @param clientId New value of clientId.
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * Gets projects.
     * @return the projects
     */
    public List<Project> getProjects() {
        return projects;
    }

    /**
     * Sets projects.
     * @param projects the projects
     */
    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Client{");
        sb.append("clientId='").append(clientId).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", budgetYear='").append(budgetYear).append('\'');
        sb.append(", projects=").append(projects);
        sb.append('}');
        return sb.toString();
    }
}
