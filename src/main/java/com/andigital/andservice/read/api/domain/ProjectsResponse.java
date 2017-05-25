package com.andigital.andservice.read.api.domain;

import java.util.List;

import org.springframework.data.annotation.Id;

public class ProjectsResponse {
	@Id
	private String clientId;
	private List<String> projects;
	/**
	 * @return the clientId
	 */
	public String getClientId() {
		return clientId;
	}
	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	/**
	 * @return the projects
	 */
	public List<String> getProjects() {
		return projects;
	}
	/**
	 * @param projects the projects to set
	 */
	public void setProjects(List<String> projects) {
		this.projects = projects;
	}
	
}
