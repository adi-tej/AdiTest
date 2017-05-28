package com.andigital.andservice.domain;

/**
 * This will be appended to Rest Response in case of error for ANDService API endpoints
 */
public class ErrorResponse {
    private String message;
    private String code;
    private StackTraceElement[] details;

    /**
     * Instantiates a new Error response.
     */
    public ErrorResponse() {
         // Default Constructor
    }

    /**
     * Instantiates a new Error response.
     *
     * @param message   the message
     * @param code the error code
     */
    public ErrorResponse(String message, String code, StackTraceElement[] details) {
        this.code = code;
        this.message = message;
        this.details = details;
    }

    /**
     * Gets message.
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message.
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }


    /**
     * Gets error code.
     * @return the code
     */
    public String getcode() {
        return code;
    }

    /**
     * Sets error code.
     * @param code the code to set
     */
    public void setcode(String code) {
        this.code = code;
    }

    /**
     * Sets new details.
     * @param details New value of details.
     */
    public void setDetails(StackTraceElement[] details) {
        this.details = details;
    }

    /**
     * Gets details.
     * @return Value of details.
     */
    public StackTraceElement[] getDetails() {
        return details;
    }
}