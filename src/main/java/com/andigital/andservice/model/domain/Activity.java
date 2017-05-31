package com.andigital.andservice.model.domain;

import com.andigital.andservice.model.Expense;

import java.util.Arrays;

/**
 * Created by rakeshchoudhary on 5/24/17.
 */
public class Activity {

    private Long activityId;
    private Long projectId;
    private User[] users;
    private Expense[] expenses;

    /**
     * Gets the activity id.
     *
     * @return the activity id
     */
    public Long getActivityId() {
        return activityId;
    }

    /**
     * Sets the activity id.
     *
     * @param activityId the new activity id
     */
    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    /**
     * Gets the project id.
     *
     * @return the project id
     */
    public Long getProjectId() {
        return projectId;
    }

    /**
     * Sets the project id.
     *
     * @param projectId the new project id
     */
    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    /**
     * Gets the resources.
     *
     * @return the resources
     */
    public User[] getUsers() {
        return users;
    }

    /**
     * Sets the resources.
     *
     * @param users the new resources
     */
    public void setUsers(User[] users) {
        this.users = users;
    }

    /**
     * Gets the expenses.
     *
     * @return the expenses
     */
    public Expense[] getExpenses() {
        return expenses;
    }

    /**
     * Sets the expenses.
     *
     * @param expenses the new expenses
     */
    public void setExpenses(Expense[] expenses) {
        this.expenses = expenses;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Activity{");
        sb.append("activityId=").append(activityId);
        sb.append(", projectId=").append(projectId);
        sb.append(", resources=").append(users == null ? "null" : Arrays.asList(users).toString());
        sb.append(", expenses=").append(expenses == null ? "null" : Arrays.asList(expenses).toString());
        sb.append('}');
        return sb.toString();
    }
}
