package com.andigital.andservice.model;

import com.andigital.andservice.model.domain.Project;
import com.andigital.andservice.model.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;


/**
 * Created by rakeshchoudhary on 5/24/17.
 */
public class DashboardResponse {
    private Project project;

    private String actualSpent;

    private String forecastSpent;

    private String forecastVariance;
    @JsonIgnoreProperties({"user_id","baseRate","extensionRequest","entryUnit","activityDate","clientId","role"})
    private User serviceDeliveryManager;

    private Schedule[] schedule;


    /**
     * Gets the actual spent.
     *
     * @return the actual spent
     */
    public String getActualSpent() {
        return actualSpent;
    }

    /**
     * Sets the actual spent.
     *
     * @param actualSpent the new actual spent
     */
    public void setActualSpent(String actualSpent) {
        this.actualSpent = actualSpent;
    }

    /**
     * Gets the forecast spent.
     *
     * @return the forecast spent
     */
    public String getForecastSpent() {
        return forecastSpent;
    }

    /**
     * Sets the forecast spent.
     *
     * @param forecastSpent the new forecast spent
     */
    public void setForecastSpent(String forecastSpent) {
        this.forecastSpent = forecastSpent;
    }

    /**
     * Gets the service delivery lead id.
     *
     * @return the service delivery lead id
     */
    public User getServiceDeliveryManager() {
        return serviceDeliveryManager;
    }

    /**
     * Sets the service delivery lead id.
     *
     * @param serviceDeliveryManager the new service delivery lead id
     */
    public void setServiceDeliveryManager(User serviceDeliveryManager) {
        this.serviceDeliveryManager = serviceDeliveryManager;
    }

    /**
     * Gets the schedule.
     *
     * @return the schedule
     */
    public Schedule[] getSchedule() {
        return schedule;
    }

    /**
     * Sets the monthly data.
     *
     * @param schedule the new monthly data
     */
    public void setSchedule(Schedule[] schedule) {
        this.schedule = schedule;
    }

    /**
     * Gets the forecast variance.
     *
     * @return the forecast variance
     */
    public String getForecastVariance() {
        return forecastVariance;
    }

    /**
     * Sets the forcast variance.
     *
     * @param forecastVariance the new forcast variance
     */
    public void setForecastVariance(String forecastVariance) {
        this.forecastVariance = forecastVariance;
    }

    /**
     * Gets the project.
     *
     * @return the project
     */
    public Project getProject() {
        return project;
    }

    /**
     * Sets the project.
     *
     * @param project the new project
     */
    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DashboardResponse{");
        sb.append("project=").append(project);
        sb.append(", actualSpent='").append(actualSpent).append('\'');
        sb.append(", forecastSpent='").append(forecastSpent).append('\'');
        sb.append(", forecastVariance='").append(forecastVariance).append('\'');
        sb.append(", serviceDeliveryManager=").append(serviceDeliveryManager);
        sb.append(", schedule=").append(schedule == null ? "null" : Arrays.asList(schedule).toString());
        sb.append('}');
        return sb.toString();
    }
}
