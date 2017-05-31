package com.andigital.andservice.controller;

import com.andigital.andservice.common.AndServiceErrorMessage;
import com.andigital.andservice.common.Constant;
import com.andigital.andservice.model.DashboardResponse;
import com.andigital.andservice.model.ErrorResponse;
import com.andigital.andservice.model.RestResponse;
import com.andigital.andservice.model.domain.Client;
import com.andigital.andservice.model.domain.SystemProperties;
import com.andigital.andservice.services.ClientService;
import com.andigital.andservice.services.ProjectService;
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

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
    private SystemPropertiesService systemPropertiesService;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private ClientService clientService;

	private RestResponse restResponse = null;
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
	 *
	 * @return the response entity
	 */
	@RequestMapping(value = "/systemproperties", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public RestResponse<Object> getSystemProperties(){
		logger.debug("In getSystemProperties method {}",new Date());
		try {
			List<SystemProperties> systemPropertiesList = systemPropertiesService.getSystemProperties();
			if(systemPropertiesList !=null && !systemPropertiesList.isEmpty()) {
				restResponse = new RestResponse<>(Constant.SUCCESS, Constant.SYSTEM_PROPERTIES,systemPropertiesList.get(0));
			}else{
				logger.error("Error in getSystemProperties method : No data found. ");
				restResponse = new RestResponse<>(Constant.ERROR, new ErrorResponse(AndServiceErrorMessage.SYS_PROPERTIES_NA.getReasonPhrase(), AndServiceErrorMessage.SYS_PROPERTIES_NA.code()));
			}
		} catch (Exception e) {
			logger.error("Error in getSystemProperties method {}", e);
			restResponse = new RestResponse<>(Constant.ERROR, new ErrorResponse(AndServiceErrorMessage.APPLICATION_ERROR.getReasonPhrase(), AndServiceErrorMessage.APPLICATION_ERROR.code()));
		}
		return restResponse;
	}

	/**
	 * Rest API Endpoint to get List of Projects of Clients for a given userId.
	 *
	 * @param userid the user id
	 * @return the rest response
	 */
	@RequestMapping(value = "/user/{userid}/projects", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public RestResponse getProjectsList(@PathVariable String userid){
		logger.debug("In getProjectsList method user Id :{}",userid);
		try {
			if(userid!=null) {
				List<Client> projectsList = clientService.getClientProjectsByUserId(userid);
				if (projectsList != null && !projectsList.isEmpty()) {
					restResponse = new RestResponse<>(Constant.SUCCESS, Constant.CLIENTS, projectsList);
				} else {
					logger.error("Error in getProjectsList method : No data found. ");
					restResponse = new RestResponse<>(Constant.ERROR, new ErrorResponse(AndServiceErrorMessage.CLIENTS_PROJECTS_NA.getReasonPhrase(), AndServiceErrorMessage.CLIENTS_PROJECTS_NA.code()));
				}
			}else{
				logger.error("Error in getProjectsList method : UserId is null. ");
				restResponse = new RestResponse<>(Constant.ERROR, new ErrorResponse(AndServiceErrorMessage.USER_ID_NA.getReasonPhrase(), AndServiceErrorMessage.USER_ID_NA.code()));
			}
		} catch (Exception e) {
			logger.error("Error in getProjectsList method {}", e);
			restResponse = new RestResponse<>(Constant.ERROR, new ErrorResponse(AndServiceErrorMessage.APPLICATION_ERROR.getReasonPhrase(), AndServiceErrorMessage.APPLICATION_ERROR.code()));
		}
		return restResponse;
	}

    /**
     * Rest API Endpoint to get dashboard data for a given project id.
     *
     * @param projectId the project id
     * @return the project by id
     */
    @RequestMapping(path = "/project/{projectid}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE } )
    public RestResponse<Object> getProjectById(@PathVariable String projectId) {
        logger.debug("In getProjectById method,getting details of project {}",projectId);
        try {
            DashboardResponse dashboardResponse = projectService.getProjectById(projectId);
            if (dashboardResponse != null) {
                restResponse = new RestResponse<>(Constant.SUCCESS,"dashboard", dashboardResponse);
            }else {
                logger.error("Error in getProjectById method : No data found. ");
				restResponse = new RestResponse<>(Constant.ERROR, new ErrorResponse(AndServiceErrorMessage.PROJECT_NA.getReasonPhrase(), AndServiceErrorMessage.PROJECT_NA.code()));
            }
        }catch (Exception e) {
            logger.error("Error in getProjectById method {}", e);
			restResponse = new RestResponse<>(Constant.ERROR, new ErrorResponse(AndServiceErrorMessage.APPLICATION_ERROR.getReasonPhrase(), AndServiceErrorMessage.APPLICATION_ERROR.code()));
        }
        return restResponse;
    }

}
