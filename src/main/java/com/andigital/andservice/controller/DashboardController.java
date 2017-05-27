package com.andigital.andservice.controller;

import com.andigital.andservice.common.AndServiceErrorMessage;
import com.andigital.andservice.common.Constant;
import com.andigital.andservice.domain.Project;
import com.andigital.andservice.domain.SystemProperties;
import com.andigital.andservice.services.ProjectsService;
import com.andigital.andservice.services.SystemPropertiesService;
import com.andigital.andservice.web.ErrorResponse;
import com.andigital.andservice.web.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
	 * #id: the id of the system properties document in MongoDB
	 * #lastUpdatedDate: the system last updated date in format MM/DD/YY
	 *
	 * @return the response entity
	 */
	@RequestMapping(value = "/systemproperties", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public RestResponse<?> getSystemProperties(){
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

	@RequestMapping(value = "/projects", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public Project projectsList() throws Exception {
		return projectsService.getProjects().get(0);
	}
}
