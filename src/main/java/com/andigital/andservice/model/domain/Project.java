package com.andigital.andservice.model.domain;

import com.andigital.andservice.common.Constant;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by rakeshchoudhary on 5/24/17.
 */
@Document(collection="project")
public class Project {

    private String id;

    private String title;

    @Field("client_id")
    private String clientId;

    @Field("start_date")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern= Constant.DATE_FORMAT_DD_MM_YYYY)
    private Date startDate;

    @Field("end_date")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern= Constant.DATE_FORMAT_DD_MM_YYYY)
    private Date endDate;
    @Field("planned_budget")
    private String plannedBudget;
    @JsonIgnoreProperties({"user_id","baseRate","extensionRequest","entryUnit","clientId","activityDate"})
    private List<User> users;

    public Project() {
        // Default Constructor
    }

    /**
     * Instantiates a new Project.
     *
     * @param title         the title
     * @param clientId      the client id
     * @param startDate     the start date
     * @param endDate       the end date
     * @param plannedBudget the planned budget
     * @param users         the users
     */
    public Project(String title, String clientId, Date startDate, Date endDate, String plannedBudget, User[] users) {
        this.title = title;
        this.clientId = clientId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.plannedBudget = plannedBudget;
        if(users!=null) {
            this.users = Arrays.asList(users);
        }
    }

    /**
     * Gets the project id.
     * @return the project id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the project id.
     * @param id the new project id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the title.
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title.
     * @param title the new title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the client id.
     * @return the client id
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * Sets the client id.
     * @param clientId the new client id
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * Gets the start date.
     * @return the start date
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date.
     * @param startDate the new start date
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets the end date.
     * @return the end date
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date.
     * @param endDate the new end date
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets the planned budget.
     * @return the planned budget
     */
    public String getPlannedBudget() {
        return plannedBudget;
    }

    /**
     * Sets the planned budget.
     * @param plannedBudget the new planned budget
     */
    public void setPlannedBudget(String plannedBudget) {
        this.plannedBudget = plannedBudget;
    }

    /**
     * Gets the users.
     * @return the users
     */
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Project{");
        sb.append("projectId='").append(id).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", clientId='").append(clientId).append('\'');
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", plannedBudget='").append(plannedBudget).append('\'');
        sb.append(", users=").append(users == null ? "null" : Arrays.asList(users).toString());
        sb.append('}');
        return sb.toString();
    }
}
