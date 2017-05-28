package com.andigital.andservice.exception;

/**
 * The And rest service exception.
 * This will be used for application level exceptions.
 */
public class ANDApplicationException extends Exception{
    private String errorMessage;

    /**
     * Gets error message.
     * @return the error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Instantiates a new And rest service exception.
     * @param errorMessage the error message
     */
    public ANDApplicationException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    /**
     * Instantiates a new AND rest service exception.
     */
    public ANDApplicationException() {
        super();
    }
}
