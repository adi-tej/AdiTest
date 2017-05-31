package com.andigital.andservice.model;

/**
 * Created by rakeshchoudhary on 5/24/17.
 */
public class Expense {

    private String type;
    
    private String description;
    
    private String expenses;
    
    private String actualExpenseCost;
    
    private String plannedExpenseCost;

    /**
     * Gets the type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type.
     *
     * @param type the new type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param description the new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the expenses.
     *
     * @return the expenses
     */
    public String getExpenses() {
        return expenses;
    }

    /**
     * Sets the expenses.
     *
     * @param expenses the new expenses
     */
    public void setExpenses(String expenses) {
        this.expenses = expenses;
    }

    /**
     * Gets the actual expense cost.
     *
     * @return the actual expense cost
     */
    public String getActualExpenseCost() {
        return actualExpenseCost;
    }

    /**
     * Sets the actual expense cost.
     *
     * @param actualExpenseCost the new actual expense cost
     */
    public void setActualExpenseCost(String actualExpenseCost) {
        this.actualExpenseCost = actualExpenseCost;
    }

    /**
     * Gets the planned expense cost.
     *
     * @return the planned expense cost
     */
    public String getPlannedExpenseCost() {
        return plannedExpenseCost;
    }

    /**
     * Sets the planned expense cost.
     *
     * @param plannedExpenseCost the new planned expense cost
     */
    public void setPlannedExpenseCost(String plannedExpenseCost) {
        this.plannedExpenseCost = plannedExpenseCost;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Expense{");
        sb.append("type='").append(type).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", expenses='").append(expenses).append('\'');
        sb.append(", actualExpenseCost='").append(actualExpenseCost).append('\'');
        sb.append(", plannedExpenseCost='").append(plannedExpenseCost).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
