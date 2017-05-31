package com.andigital.andservice.common;

/**
 * UserType enum will hold all defined user type in system
 */
public enum UserType {

	EMPLOYEE("employee"),
	CLIENT("client"),
	CONTRACTOR("contractor");
	private final String type;

	UserType(String type) {
		this.type = type;
	}

	/**
	 * Returns the user type.
	 * @return the type
	 */
	public String getType() {
		return type;
	}
}
