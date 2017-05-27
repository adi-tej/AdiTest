package com.andigital.andservice.common;

/**
 * New error code has been introduced to show each code unique. Generic error
 * code will starts from ANDS-00x For Dashboard related exception : error code
 * will start with ANDS-1xx For Commercials related exception : error code will
 * start with ANDS-2xx For Team related exception : error code will start with
 * ANDS-3xx For Schedule related exception : error code will start with ANDS-4xx
 */
public enum AndServiceErrorMessage {

	// Generic error
	NO_DATA_FOUND("ANDS-001", "NO DATA FOUND"),
	APPLICATION_ERROR("ANDS-002","Application error"),

	// Dashboard error
	MISSING_USER_ID("ANDS-100", "User Id is missing."),
	SYS_PROPERTIES_NA("ANDS-101","No System Properties are available");

	private final String code;

	private final String reasonPhrase;

	AndServiceErrorMessage(String code, String reasonPhrase) {
		this.code = code;
		this.reasonPhrase = reasonPhrase;
	}

	/**
	 * Return the error code.
	 */
	public String code() {
		return this.code;
	}

	/**
	 * Return the reason phrase of this error code.
	 */
	public String getReasonPhrase() {
		return this.reasonPhrase;
	}
}
