package com.andigital.andservice.repository.impl;

import com.andigital.andservice.common.UserType;
import com.andigital.andservice.model.domain.Client;
import com.andigital.andservice.repository.ClientRepository;
import com.mongodb.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation class for client repository
 */
@Repository
public class ClientRepositoryImpl implements ClientRepository {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Client> getClientProjectsByUserId(String userId) {

        logger.debug("Getting Clients and Projects of user Id {}: ",userId);

        List<Client> clientList = new ArrayList<>();
        String CLIENT_ID = "client_id";

        DBCollection userCollection = mongoTemplate.getCollection("user");
        DBCollection clientCollection = mongoTemplate.getCollection("client");

        DBObject query = new BasicDBObject("id",userId);
        BasicDBObject selector = new BasicDBObject(CLIENT_ID,"1").append("type","1").append("_id", "0");
        DBCursor cursor = userCollection.find(query,selector);
        List<DBObject> clientObjList = cursor.toArray();
        if(!(clientObjList==null || clientObjList.isEmpty())){
            List<DBObject> dbOperation = new ArrayList<>();
            String userType = (String) clientObjList.get(0).get("type");
            if (UserType.CLIENT.getType().equals(userType)||UserType.CONTRACTOR.getType().equals(userType))
            {
                DBObject match = new BasicDBObject("$match", new BasicDBObject("Id", clientObjList.get(0).get(CLIENT_ID)));
                dbOperation.add(match);
            }
            DBObject lookup = new BasicDBObject("$lookup", new BasicDBObject("from", "project").append("localField", "Id").append("foreignField", CLIENT_ID).append("as", "projects"));
            DBObject projection = new BasicDBObject("$project", new BasicDBObject("_id", 0).append("Id", 1).append("name", 1).append("budget_year",1).append("projects._id", 1).append("projects.title", 1).append("projects.start_date", 1).append("projects.end_date", 1));
            DBObject sort = new BasicDBObject("$sort", new BasicDBObject("name", 1).append("projects.title", 1));

            dbOperation.add(lookup);
            dbOperation.add(projection);
            dbOperation.add(sort);
            AggregationOutput output = clientCollection.aggregate(dbOperation);
            if(output != null && output.results() != null){
                for (DBObject dbObject : output.results())
                {
                    Client client = mongoTemplate.getConverter().read(Client.class, dbObject);
                    clientList.add(client);
                }
            }else {
                logger.debug("Error in getClientProjectsByUserId method, Query result is empty ");
            }
        }else {
            logger.debug("clientObjList is empty from DB: No clients with userId : {}",userId);
        }
        return clientList;
    }
}
