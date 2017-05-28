package com.andigital.andservice.repository;

import com.andigital.andservice.domain.ClientsList;
import com.mongodb.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * The type Projects repository.
 */
@Repository
public class ProjectsRepositoryImpl implements ProjectsRepository {
	@Autowired
	private MongoTemplate mongoTemplate;

	private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public List<ClientsList> getProjects(String userId) {
		List<ClientsList> results = new ArrayList<>();
		String CLIENT_ID = "client_id";

		DBCollection userCollection = mongoTemplate.getCollection("user");
		DBCollection clientCollection = mongoTemplate.getCollection("client");

		//Query to select client_id of a given user_id from user collection
		DBObject query = new BasicDBObject("id",userId);
		BasicDBObject selector = new BasicDBObject(CLIENT_ID,"1").append("type","1").append("id", "0");
		DBCursor cursor = userCollection.find(query,selector);
		List<DBObject> clientList = cursor.toArray();
		if(!cursor.toArray().isEmpty()&&cursor.toArray()!=null){
			List<DBObject> dbOperation = new ArrayList<>();
			String userType = (String) clientList.get(0).get("type");
			/*
			 * Query to get list of projects of resulted client_ids from project collection.
			 *
			 * If user is of type External or Contractor, client specific projects will be returned
			 * If user is of type Employee all projects of all clients
			 */
			if ("external".equals(userType))
			{
				DBObject match = new BasicDBObject("$match", new BasicDBObject("Id", clientList.get(0).get(CLIENT_ID)));
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
				Iterator<DBObject> dbObject = output.results().iterator();
				while (dbObject.hasNext()) {
					DBObject obj = dbObject.next();
					ClientsList clientsList = mongoTemplate.getConverter().read(ClientsList.class, obj);
					results.add(clientsList);
				}
			}else {
				logger.info("Output Null");
			}
		}else {
			logger.info("clientList Nul: No clients with userId");
		}
		return results;
	}
}

