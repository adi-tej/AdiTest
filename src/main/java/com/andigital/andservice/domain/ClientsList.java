package com.andigital.andservice.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * The type Projects list.
 */
@Document
public class ClientsList {

    @Field("Id")
    private String id;

    @Field("name")
    private String name;

    @Field("budget_year")
    private String budgetYear;

    @Field("projects")
    private List<Projects> projects;

    public ClientsList() {
        // Default Constructor
    }

    public ClientsList(String id, String name, String budgetYear, List<Projects> projects) {
        this.id = id;
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
     * Sets new id.
     * @param id New value of id.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Sets new name.
     * @param name New value of name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets id.
     * @return Value of id.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets new budgetYear.
     * @param budgetYear New value of budgetYear.
     */
    public void setBudgetYear(String budgetYear) {
        this.budgetYear = budgetYear;
    }

    /**
     * Gets budgetYear.
     * @return Value of budgetYear.
     */
    public String getBudgetYear() {
        return budgetYear;
    }

    /**
     * Sets new projects.
     * @param projects New value of projects.
     */
    public void setProjects(List<Projects> projects) {
        this.projects = projects;
    }

    /**
     * Gets projects.
     * @return Value of projects.
     */
    public List<Projects> getProjects() {
        return projects;
    }

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ClientsList{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", budgetYear='").append(budgetYear).append('\'');
        sb.append(", projects=").append(projects);
        sb.append('}');
        return sb.toString();
    }
}
