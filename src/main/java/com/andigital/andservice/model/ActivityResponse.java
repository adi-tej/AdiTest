package com.andigital.andservice.model;

/**
 * Created by rakeshchoudhary on 5/24/17.
 */
public class ActivityResponse {

    private Long resourceId;
    private Integer plannedBillableDays;
    private Integer actualBillableDays;
    private Double plannedCost;
    private Double actualCost;
    private Integer absentDays;

    /**
     * Gets the resource id.
     *
     * @return the resource id
     */
    public Long getResourceId() {
        return resourceId;
    }

    /**
     * Sets the resource id.
     *
     * @param resourceId the new resource id
     */
    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
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
     * Gets the planned cost.
     *
     * @return the planned cost
     */
    public Double getPlannedCost() {
        return plannedCost;
    }

    /**
     * Sets the planned cost.
     *
     * @param plannedCost the new planned cost
     */
    public void setPlannedCost(Double plannedCost) {
        this.plannedCost = plannedCost;
    }

    /**
     * Gets the actual cost.
     *
     * @return the actual cost
     */
    public Double getActualCost() {
        return actualCost;
    }

    /**
     * Sets the actual cost.
     *
     * @param actualCost the new actual cost
     */
    public void setActualCost(Double actualCost) {
        this.actualCost = actualCost;
    }
    /**
     * Gets the absent days.
     *
     * @return the absent days
     */
    public Integer getAbsentDays() {
        return absentDays;
    }

    /**
     * Sets the absent days.
     *
     * @param absentDays the new absent days
     */
    public void setAbsentDays(Integer absentDays) {
        this.absentDays = absentDays;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ActivityResponse{");
        sb.append("resourceId=").append(resourceId);
        sb.append(", plannedBillableDays=").append(plannedBillableDays);
        sb.append(", actualBillableDays=").append(actualBillableDays);
        sb.append(", plannedCost=").append(plannedCost);
        sb.append(", actualCost=").append(actualCost);
        sb.append(", absentDays=").append(absentDays);
        sb.append('}');
        return sb.toString();
    }
}
