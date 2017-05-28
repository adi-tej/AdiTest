package com.andigital.andservice.domain;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by aditeja on 5/23/2017.
 */
@Document
public class Users {

    private String user_id;
    private String role;
    private String start_date;
    private String end_date;
    private String extension_request;

    /**
     * Instantiates a new Users.
     */
    public Users(){
        // Default Constructor
    }

    /**
     * Instantiates a new Users.
     *
     * @param user_id           the user id
     * @param role              the role
     * @param start_date        the start date
     * @param end_date          the end date
     * @param extension_request the extension request
     */
    public Users(String user_id,String role,String start_date,String end_date,String extension_request){
        this.user_id=user_id;
        this.role=role;
        this.start_date=start_date;
        this.end_date=end_date;
        this.extension_request=extension_request;
    }

    /**
     * Gets user id.
     *
     * @return the user_id
     */
    public String getUser_id() {
        return user_id;
    }

    /**
     * Sets user id.
     *
     * @param user_id the user_id to set
     */
    public void setUser_id(String user_id) {
        this.user_id = user_id;
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
     * Gets extension request.
     *
     * @return the extension_request
     */
    public String getExtension_request() {
        return extension_request;
    }

    /**
     * Sets extension request.
     *
     * @param extension_request the extension_request to set
     */
    public void setExtension_request(String extension_request) {
        this.extension_request = extension_request;
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "Users{" +
                "user_id='" + user_id + '\'' +
                ", role='" + role + '\'' +
                ", start_date='" + start_date + '\'' +
                ", end_date='" + end_date + '\'' +
                ", extension_request='" + extension_request + '\'' +
                '}';
    }
}
