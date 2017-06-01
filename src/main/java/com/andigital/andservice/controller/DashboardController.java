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

	private RestResponse restResponse;
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
	public RestResponse getSystemProperties() {
		restResponse=new RestResponse();
		try {
			List<SystemProperties> systemPropertiesList = systemPropertiesService.getSystemProperties();
			if (systemPropertiesList != null && !systemPropertiesList.isEmpty()) {
				restResponse.add(Constant.STATUS, Constant.SUCCESS);
				restResponse.add("systemproperties", systemPropertiesList.get(0));
			} else {
				logger.error("Error in getSystemProperties method : No data found. ");
				restResponse.add(Constant.STATUS, Constant.ERROR);
				restResponse.add(Constant.ERROR, new ErrorResponse(AndServiceErrorMessage.SYS_PROPERTIES_NA.getReasonPhrase(), AndServiceErrorMessage.SYS_PROPERTIES_NA.code()));
			}
		} catch (Exception e) {
				logger.error("Error in getSystemProperties method {}", e);
				restResponse.add(Constant.STATUS, Constant.ERROR);
				restResponse.add(Constant.ERROR, new ErrorResponse(AndServiceErrorMessage.APPLICATION_ERROR.getReasonPhrase(), AndServiceErrorMessage.APPLICATION_ERROR.code()));
		}
		return restResponse;
	}

	/**
	 * Rest API Endpoint to get List of Projects of Clients for a given userId.
	 *
	 * @param userId the user id
	 * @return the rest response
	 */
	@RequestMapping(value = "/user/{userid}/projects", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public RestResponse getProjectsList(@PathVariable("userid") String userId){
		restResponse = new RestResponse();
		logger.debug("In getProjectsList method user Id :{}",userId);
		try {
			if(userId!=null) {
				List<Client> projectsList = clientService.getClientProjectsByUserId(userId);
				if (projectsList != null && !projectsList.isEmpty()) {
					restResponse.add(Constant.STATUS, Constant.SUCCESS);
					restResponse.add("clients", projectsList);
				} else {
					logger.error("Error in getProjectsList method : No data found. ");
					restResponse.add(Constant.STATUS, Constant.ERROR);
					restResponse.add(Constant.ERROR, new ErrorResponse(AndServiceErrorMessage.SYS_PROPERTIES_NA.getReasonPhrase(), AndServiceErrorMessage.SYS_PROPERTIES_NA.code()));
				}
			}else{
				logger.error("Error in getProjectsList method : UserId is null. ");
				restResponse.add(Constant.STATUS, Constant.ERROR);
				restResponse.add(Constant.ERROR, new ErrorResponse(AndServiceErrorMessage.USER_ID_NA.getReasonPhrase(), AndServiceErrorMessage.USER_ID_NA.code()));
			}
		} catch (Exception e) {
			logger.error("Error in getProjectsList method {}", e);
			restResponse.add(Constant.ERROR, new ErrorResponse(AndServiceErrorMessage.APPLICATION_ERROR.getReasonPhrase(), AndServiceErrorMessage.APPLICATION_ERROR.code()));
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
    public RestResponse getProjectById(@PathVariable("projectid") String projectId) {
		restResponse = new RestResponse();
        logger.debug("In getProjectById method,getting details of project {}",projectId);
        if(projectId!=null) {
            try {
                DashboardResponse dashboardResponse = projectService.getProjectById(projectId);
                if (dashboardResponse != null) {
                    restResponse.add(Constant.STATUS, Constant.SUCCESS);
                    restResponse.add("dashboard", dashboardResponse);

                } else {
                    logger.error("Error in getProjectById method : No data found. ");
                    restResponse.add(Constant.STATUS, Constant.ERROR);
                    restResponse.add(Constant.ERROR, new ErrorResponse(AndServiceErrorMessage.PROJECT_NA.getReasonPhrase(), AndServiceErrorMessage.PROJECT_NA.code()));
                }
            } catch (Exception e) {
                logger.error("Error in getProjectById method {}", e);
                restResponse.add(Constant.STATUS, Constant.ERROR);
                restResponse.add(Constant.ERROR, new ErrorResponse(AndServiceErrorMessage.APPLICATION_ERROR.getReasonPhrase(), AndServiceErrorMessage.APPLICATION_ERROR.code()));
            }
        }else{
            logger.error("Error in getProjectById method : projectId must not be null");
            restResponse.add(Constant.STATUS, Constant.ERROR);
            restResponse.add(Constant.ERROR, new ErrorResponse(AndServiceErrorMessage.MISSING_PROJECT_ID.getReasonPhrase(), AndServiceErrorMessage.MISSING_PROJECT_ID.code()));
        }
        return restResponse;
    }

}
