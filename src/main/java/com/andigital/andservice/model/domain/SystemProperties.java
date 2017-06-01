package com.andigital.andservice.model.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * The type System properties.
 */
@Document(collection = "system_properties")
public class SystemProperties {

	@Id 
	private String id;

	@Field("last_updated_date")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="dd MMM yyyy HH:mm:ss")
	private Date lastUpdatedDate;

	/**
	 * Instantiates a new System properties.
	 */
	public SystemProperties() {
		// Default Constructor
	}

	/**
	 * Instantiates a new System properties.
	 * @param id              the id
	 * @param lastUpdatedDate the last updated date
	 */
	public SystemProperties(String id, Date lastUpdatedDate) {
		this.id = id;
		this.lastUpdatedDate = lastUpdatedDate;
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
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets last updated date.
	 * @return the lastUpdatedDate
	 */
	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	/**
	 * Sets last updated date.
	 * @param lastUpdatedDate the lastUpdatedDate to set
	 */
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SystemProperties [id=");
		builder.append(id);
		builder.append(", lastUpdatedDate=");
		builder.append(lastUpdatedDate);
		builder.append("]");
		return builder.toString();
	}
}