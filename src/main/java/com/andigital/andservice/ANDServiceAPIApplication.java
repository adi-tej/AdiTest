package com.andigital.andservice;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientOptions.Builder;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

/**
 * @author premsingh
 * SpringBoot Main class.
 */
@SpringBootApplication
public class ANDServiceAPIApplication {

	@Value("${spring.data.mongodb.host}")
	private String host;
	@Value("${spring.data.mongodb.port}")
	private Integer port;
	@Value("${spring.data.mongodb.database}")
	private String dataBase;
	@Value("${spring.data.mongodb.username}")
	private String dbUserName;
	@Value("${spring.data.mongodb.password}")
	private String password;
	@Value("${mongodb.maxconnection}")
	private Integer maxConnection;
	@Value("${mongodb.minconnection}")
	private Integer minConnection;
	@Value("${mongodb.timeout}")
	private Integer connectionTimeOut;

	public static void main(String[] args) {
		SpringApplication.run(ANDServiceAPIApplication.class, args);
	}

	@Bean(name = "mongoclient")
	public MongoClient mongoclient() throws Exception {

		Builder builder = new MongoClientOptions.Builder();
		builder.connectionsPerHost(maxConnection);
		builder.connectTimeout(connectionTimeOut);
		builder.minConnectionsPerHost(minConnection);
		MongoCredential credential = MongoCredential.createCredential(dbUserName,
				dataBase, password.toCharArray());
		ServerAddress mongoUri = new ServerAddress(host, port);
		return new MongoClient(mongoUri, Arrays.asList(credential), builder.build());

	}
}
