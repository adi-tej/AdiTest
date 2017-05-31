package com.andigital.andservice.model;

import com.andigital.andservice.model.domain.Invoice;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by rakeshchoudhary on 5/24/17.
 */
public class Schedule {

    private Date month;
    
    private Integer plannedBillableDays;
    
    private String plannedCost;
    
    private Integer actualBillableDays;
    
    private String teamCost;
    
    private String totalCost;
    
    private String variance;
    
    private String availability;
    
    private ActivityResponse[] activities;
    
    private Invoice[] invoices;
    
    private Expense[] expenses;

    /**
     * Gets the month.
     *
     * @return the month
     */
    public Date getMonth() {
        return month;
    }

    /**
     * Sets the month.
     *
     * @param month the new month
     */
    public void setMonth(Date month) {
        this.month = month;
    }

    /**
     * Gets the planned billable days.
     *
     * @return the planned billable days
     */
    public Integer getPlannedBillableDays() {
        return plannedBillableDays;
    }

    /**
     * Sets the planned billable days.
     *
     * @param plannedBillableDays the new planned billable days
     */
    public void setPlannedBillableDays(Integer plannedBillableDays) {
        this.plannedBillableDays = plannedBillableDays;
    }

    /**
     * Gets the planned cost.
     *
     * @return the planned cost
     */
    public String getPlannedCost() {
        return plannedCost;
    }

    /**
     * Sets the planned cost.
     *
     * @param plannedCost the new planned cost
     */
    public void setPlannedCost(String plannedCost) {
        this.plannedCost = plannedCost;
    }

    /**
     * Gets the actual billable days.
     *
     * @return the actual billable days
     */
    public Integer getActualBillableDays() {
        return actualBillableDays;
    }

    /**
     * Sets the actual billable days.
     *
     * @param actualBillableDays the new actual billable days
     */
    public void setActualBillableDays(Integer actualBillableDays) {
        this.actualBillableDays = actualBillableDays;
    }

    /**
     * Gets the team cost.
     *
     * @return the team cost
     */
    public String getTeamCost() {
        return teamCost;
    }

    /**
     * Sets the team cost.
     *
     * @param teamCost the new team cost
     */
    public void setTeamCost(String teamCost) {
        this.teamCost = teamCost;
    }

    /**
     * Gets the total cost.
     *
     * @return the total cost
     */
    public String getTotalCost() {
        return totalCost;
    }

    /**
     * Sets the total cost.
     *
     * @param totalCost the new total cost
     */
    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost;
    }

    /**
     * Gets the variance.
     *
     * @return the variance
     */
    public String getVariance() {
        return variance;
    }

    /**
     * Sets the variance.
     *
     * @param variance the new variance
     */
    public void setVariance(String variance) {
        this.variance = variance;
    }

    /**
     * Gets the availability.
     *
     * @return the availability
     */
    public String getAvailability() {
        return availability;
    }

    /**
     * Sets the availability.
     *
     * @param availability the new availability
     */
    public void setAvailability(String availability) {
        this.availability = availability;
    }

    /**
     * Gets the activities.
     *
     * @return the activities
     */
    public ActivityResponse[] getActivities() {
        return activities;
    }

    /**
     * Sets the activities.
     *
     * @param activities the new activities
     */
    public void setActivities(ActivityResponse[] activities) {
        this.activities = activities;
    }

    /**
     * Gets the invoices.
     *
     * @return the invoices
     */
    public Invoice[] getInvoices() {
        return invoices;
    }

    /**
     * Sets the invoices.
     *
     * @param invoices the new invoices
     */
    public void setInvoices(Invoice[] invoices) {
        this.invoices = invoices;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Schedule{");
        sb.append("month=").append(month);
        sb.append(", plannedBillableDays=").append(plannedBillableDays);
        sb.append(", plannedCost='").append(plannedCost).append('\'');
        sb.append(", actualBillableDays=").append(actualBillableDays);
        sb.append(", teamCost='").append(teamCost).append('\'');
        sb.append(", totalCost='").append(totalCost).append('\'');
        sb.append(", variance='").append(variance).append('\'');
        sb.append(", availability='").append(availability).append('\'');
        sb.append(", activities=").append(activities == null ? "null" : Arrays.asList(activities).toString());
        sb.append(", invoices=").append(invoices == null ? "null" : Arrays.asList(invoices).toString());
        sb.append(", expenses=").append(expenses == null ? "null" : Arrays.asList(expenses).toString());
        sb.append('}');
        return sb.toString();
    }
}
