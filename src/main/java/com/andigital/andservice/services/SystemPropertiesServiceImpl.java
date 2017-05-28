package com.andigital.andservice.services;

import com.andigital.andservice.domain.SystemProperties;
import com.andigital.andservice.repository.SystemPropertiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author premsingh
 * Implementation class for SystemPropertiesService interface.
 */
@Service
public class SystemPropertiesServiceImpl implements SystemPropertiesService {

	/**
	 * The System properties repository.
	 */
	@Autowired
	private SystemPropertiesRepository systemPropertiesRepository;


	/**
	 * Instantiates a new System properties service.
	 */
	public SystemPropertiesServiceImpl() {
		// Default constructor.
	}

	/* (non-Javadoc)
	 * @see com.andigital.andservice.services.DashboardService#getSystemProperties()
	 */
	@Override
	public List<SystemProperties> getSystemProperties() throws Exception {
		return systemPropertiesRepository.findAll();
	}

}
