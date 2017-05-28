package com.andigital.andservice.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * The type Projects.
 */
@Document
public class Projects {

    @Id
    private String id;

    @Field("title")
    private String name;

    @Field("start_date")
    private String startDate;

    @Field("end_date")
    private String endDate;

    /**
     * Instantiates a new Projects.
     */
    public Projects() {
        // Default Constructor
    }

    /**
     * Instantiates a new Projects.
     *
     * @param id        the id
     * @param name      the name
     * @param startDate the start date
     * @param endDate   the end date
     */
    public Projects(String id, String name, String startDate, String endDate) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Gets id.
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets name.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets start date.
     * @return the start date
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Sets start date.
     * @param startDate the start date
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets end date.
     * @return the end date
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Sets end date.
     * @param endDate the end date
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Projects{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", startDate='").append(startDate).append('\'');
        sb.append(", endDate='").append(endDate).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
