package com.andigital.andservice.exception;

/**
 * The And rest service exception.
 * This will be used for application level exceptions.
 */
public class ANDApplicationException extends RuntimeException{
    private String errorMessage;
    private Throwable throwable;

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

    public ANDApplicationException(Throwable t) {
        super(t);
        this.throwable = t;
    }

    public ANDApplicationException(String arg0, Throwable arg1)  {
        super(arg1);
        this.errorMessage = arg0;
        this.throwable = arg1;
    }

    /**
     * Gets error message.
     * @return the error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @return the throwable
     */
    public Throwable getThrowable() {
        return throwable;
    }
    /**
     * @param throwable the throwable to set
     */
    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
