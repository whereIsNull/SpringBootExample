package com.mymongo.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.mymongo.springboot.repository")
public class MongoApplicationConfig extends AbstractMongoClientConfiguration {

	@Override
	protected String getDatabaseName() {
	  return "movies";
	}
	
	@Override
	protected String getMappingBasePackage() {
	  return "com.mymongo.springboot.repository";
	}
}
