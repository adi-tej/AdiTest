package com.andigital.andservice.model.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * Created by rakeshchoudhary on 5/24/17.
 */
@Document(collection = "user")
public class User {
    @Field("id")
    private String userId;
    @Field("client_id")
    private String clientId;
    @Field("first_name")
    private String firstName;
    @Field("last_name")
    private String lastName;
    private String type;
    private String role;
    private String email;
    private String mobile;
    @Field("profile_image")
    private String profileImg;
    @Field("profile_description")
    private String profileDescription;
    @Field("start_date")
    private Date startDate;
    @Field("end_date")
    private Date endDate;
    @Field("extension_request")
    private Boolean extensionRequest;
    @Field("base_rate")
    private String baseRate;
    @Field("entry_unit")
    private String entryUnit;
    @Field("activity_date")
    private Date activityDate;

    /**
     * Gets the user id.
     * @return the user id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the user id.
     * @param userId the new user id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Gets the first name.
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name.
     * @param firstName the new first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name.
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name.
     * @param lastName the new last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets type.
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type.
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
    }
    /**
     * Gets the role.
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role.
     * @param role the new role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Gets the email.
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email.
     * @param email the new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the mobile.
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * Sets the mobile.
     * @param mobile the new mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * Gets the profile img.
     * @return the profile img
     */
    public String getProfileImg() {
        return profileImg;
    }

    /**
     * Sets the profile img.
     * @param profileImg the new profile img
     */
    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    /**
     * Gets the profile description.
     * @return the profile description
     */
    public String getProfileDescription() {
        return profileDescription;
    }

    /**
     * Sets the profile description.
     * @param profileDescription the new profile description
     */
    public void setProfileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
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
     * Gets the extension request.
     * @return the extension request
     */
    public Boolean getExtensionRequest() {
        return extensionRequest;
    }

    /**
     * Sets the extension request.
     * @param extensionRequest the new extension request
     */
    public void setExtensionRequest(Boolean extensionRequest) {
        this.extensionRequest = extensionRequest;
    }

    /**
     * Gets the base rate.
     * @return the base rate
     */
    public String getBaseRate() {
        return baseRate;
    }

    /**
     * Sets the base rate.
     * @param baseRate the new base rate
     */
    public void setBaseRate(String baseRate) {
        this.baseRate = baseRate;
    }

    /**
     * Gets the entry unit.
     * @return the entry unit
     */
    public String getEntryUnit() {
        return entryUnit;
    }

    /**
     * Sets the entry unit.
     * @param entryUnit the new entry unit
     */
    public void setEntryUnit(String entryUnit) {
        this.entryUnit = entryUnit;
    }

    /**
     * Gets the date.
     * @return the date
     */
    public Date getActivityDate() {
        return activityDate;
    }

    /**
     * Sets the date.
     * @param date the new date
     */
    public void setActivityDate(Date date) {
        this.activityDate = date;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("userId='").append(userId).append('\'');
        sb.append(", clientId='").append(clientId).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", role='").append(role).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", mobile='").append(mobile).append('\'');
        sb.append(", profileImg='").append(profileImg).append('\'');
        sb.append(", profileDescription='").append(profileDescription).append('\'');
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", extensionRequest=").append(extensionRequest);
        sb.append(", baseRate='").append(baseRate).append('\'');
        sb.append(", entryUnit='").append(entryUnit).append('\'');
        sb.append(", activityDate=").append(activityDate);
        sb.append('}');
        return sb.toString();
    }
}
