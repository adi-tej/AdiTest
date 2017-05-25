package com.andigital.andservice.read.api.repository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.hateoas.alps.Doc;
import com.andigital.andservice.read.api.domain.Project;
import com.andigital.andservice.read.api.domain.ProjectsResponse;
import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.DBObjectCodec;
import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class ProjectsRepositoryImpl implements ProjectsRepository {
	@Autowired
	private MongoTemplate mongoTemplate;
	@Autowired
	@Qualifier("mongoclient")
	private MongoClient mongoClient;
	 DB db = mongoClient.getDB("ANDTest");
	// DBCollection collection = db.getCollection("project");
	
	
	@Override
	public <S extends Project> List<S> save(Iterable<S> entites) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Project> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Project> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Project> S insert(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Project> List<S> insert(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Project> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Project> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Project> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Project> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Project findOne(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Project> findAll(Iterable<String> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Project entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Iterable<? extends Project> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends Project> S findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Project> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Project> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends Project> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public List<ProjectsResponse> getProjectsResponse() {
		Aggregation agg = newAggregation(group("clientId").push("title").as("projects"));
		AggregationResults<ProjectsResponse> groupResults= mongoTemplate.aggregate(agg,Project.class, ProjectsResponse.class);

	
		/*List<ProjectsResponse> responseList=new ArrayList<>();
		 final DBObject groupIdFields = new BasicDBObject("_id", "$clientId");
		 groupIdFields.put("projects", new BasicDBObject("$push","$title"));
		 final DBObject group = new BasicDBObject("$group", groupIdFields);
		 AggregationOutput output = collection.aggregate(Arrays.asList(group));
		 Iterable<DBObject> list=output.results();
		 if(list!=null){
		 for (DBObject dbObject:list){
			 
			 responseList.add((ProjectsResponse)dbObject);
		 }
		 }
		 if(responseList!=null)
		return responseList ;
		 else*/
		return  groupResults.getMappedResults();	
	}
	
	
	}

