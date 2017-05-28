package com.andigital.andservice.controller;

import com.andigital.andservice.common.AndServiceErrorMessage;
import com.andigital.andservice.common.Constant;
import com.andigital.andservice.domain.ClientsList;
import com.andigital.andservice.domain.ErrorResponse;
import com.andigital.andservice.domain.RestResponse;
import com.andigital.andservice.domain.SystemProperties;
import com.andigital.andservice.services.ProjectsService;
import com.andigital.andservice.services.SystemPropertiesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author premsingh
 * Rest Controller class for Dashboard.
 */
@RestController
@RequestMapping("/dashboard")
public class DashboardController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SystemPropertiesService systemPropertiesService;

	@Autowired
	private ProjectsService projectsService;

	/**
	 * Test string.
	 *
	 * @return the string
	 */
	@RequestMapping(path = "/test", method = RequestMethod.GET)
	public String test() {
		logger.debug("Inside test method {}",new Date());
		return "Rest Service is up and running for DashBoard!!";
	}

	/**
	 * Rest API Endpoint to get System Properties
	 * Returns a JSON Object that contains following values
	 * #id: The id of the system properties document in MongoDB
	 * #lastUpdatedDate: The system last updated date in format MM/DD/YY
	 *
	 * @return the response entity
	 */
	@RequestMapping(value = "/systemproperties", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public RestResponse getSystemProperties(){
		logger.debug("In getSystemProperties method {}",new Date());
		RestResponse<?> restResponse;
		try {
			List<SystemProperties> systemPropertiesList = systemPropertiesService.getSystemProperties();
			if(systemPropertiesList !=null && !systemPropertiesList.isEmpty()) {
				restResponse = new RestResponse<>(Constant.SUCCESS, "systemproperties",systemPropertiesList.get(0));
			}else{
				logger.error("Error in getSystemProperties method : No data found. ");
				restResponse = new RestResponse<>(Constant.ERROR, new ErrorResponse(AndServiceErrorMessage.SYS_PROPERTIES_NA.getReasonPhrase(), AndServiceErrorMessage.SYS_PROPERTIES_NA.code(),null));
			}
		} catch (Exception e) {
			logger.error("Error in getSystemProperties method {}", e);
			restResponse = new RestResponse<>(Constant.ERROR, new ErrorResponse(AndServiceErrorMessage.APPLICATION_ERROR.getReasonPhrase(), AndServiceErrorMessage.APPLICATION_ERROR.code(),e.getStackTrace()));
		}
		return restResponse;
	}

	/**
	 * Rest API Endpoint to get List of Projects of Clients for a given userId.
	 * Returns a JSON Object that contains following values
	 * #id: The id of the client document in MongoDB
	 * #name: The name of the client
	 * #budgwtYear: The budget financial year of the client
	 * #projects: Contains list of projects with the following values
	 *	 #id: The id of the project document in MongoDB
	 *	 #name: The name of the project
	 *	 #startDate: The start date of the project
	 *	 #endDate: The end date of the project
	 *
	 * @param userId the user id
	 * @return the rest response
	 */
	@RequestMapping(value = "/user/{userId}/projects", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public RestResponse getProjectsList(@PathVariable String userId){
		logger.debug("In getProjectsList method {}",new Date());
		RestResponse<?> restResponse;
		try {
			List<ClientsList> projectsList = projectsService.getProjects(userId);
			if(projectsList !=null && !projectsList.isEmpty()) {
				restResponse = new RestResponse<>(Constant.SUCCESS, "clients",projectsList);
			}else{
				logger.error("Error in getProjectsList method : No data found. ");
				restResponse = new RestResponse<>(Constant.ERROR, new ErrorResponse(AndServiceErrorMessage.SYS_PROPERTIES_NA.getReasonPhrase(), AndServiceErrorMessage.SYS_PROPERTIES_NA.code(),null));
			}
		} catch (Exception e) {
			logger.error("Error in getProjectsList method {}", e);
			restResponse = new RestResponse<>(Constant.ERROR, new ErrorResponse(AndServiceErrorMessage.APPLICATION_ERROR.getReasonPhrase(), AndServiceErrorMessage.APPLICATION_ERROR.code(),e.getStackTrace()));
		}
		return restResponse;
	}
}
