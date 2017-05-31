package com.andigital.andservice.services.impl;

import com.andigital.andservice.exception.ANDApplicationException;
import com.andigital.andservice.model.domain.Client;
import com.andigital.andservice.repository.ClientRepository;
import com.andigital.andservice.services.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation class for Client service.
 */
@Service
public class ClientServiceImpl implements ClientService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<Client> getClientProjectsByUserId(String userId) throws ANDApplicationException{
        List<Client> clientList = null;
        try {
            clientList =  clientRepository.getClientProjectsByUserId(userId);
        }
        catch (Exception e) {
            logger.error("Exception in getClientProjectsByUserId {}",e);
            throw new ANDApplicationException(e);
        }
        return clientList;
    }
}
