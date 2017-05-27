package com.andigital.andservice.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by aditeja on 5/23/2017.
 */
@Document(collection = "project")
public class Project {
    @Id
    private String id;

    private String title;
    private String client_id;
    private String planned_budget;
    private String role;
    private String start_date;
    private String end_date;
    private List<Users> users;

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets client id.
     *
     * @return the client_id
     */
    public String getClient_id() {
        return client_id;
    }

    /**
     * Sets client id.
     *
     * @param client_id the client_id to set
     */
    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    /**
     * Gets planned budget.
     *
     * @return the planned_budget
     */
    public String getPlanned_budget() {
        return planned_budget;
    }

    /**
     * Sets planned budget.
     *
     * @param planned_budget the planned_budget to set
     */
    public void setPlanned_budget(String planned_budget) {
        this.planned_budget = planned_budget;
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Gets start date.
     *
     * @return the start_date
     */
    public String getStart_date() {
        return start_date;
    }

    /**
     * Sets start date.
     *
     * @param start_date the start_date to set
     */
    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    /**
     * Gets end date.
     *
     * @return the end_date
     */
    public String getEnd_date() {
        return end_date;
    }

    /**
     * Sets end date.
     *
     * @param end_date the end_date to set
     */
    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    /**
     * Gets users.
     *
     * @return the users
     */
    public List<Users> getUsers() {
        return users;
    }

    /**
     * Sets users.
     *
     * @param users the users to set
     */
    public void setUsers(List<Users> users) {
        this.users = users;
    }

    /*
	 * @see java.lang.Object#toString()
	 */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Project{");
        sb.append("id='").append(id).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", client_id='").append(client_id).append('\'');
        sb.append(", planned_budget='").append(planned_budget).append('\'');
        sb.append(", role='").append(role).append('\'');
        sb.append(", start_date='").append(start_date).append('\'');
        sb.append(", end_date='").append(end_date).append('\'');
        sb.append(", users=").append(users);
        sb.append('}');
        return sb.toString();
    }
}
