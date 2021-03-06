package com.istiak.blooddb.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.istiak.blooddb.utils.AppConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;


public class SpringDataDBUtils {

    private static MongoOperations  mongoOperation;
    private final static Properties properties = new Properties();
    private final static Logger     logger     = LoggerFactory.getLogger(SpringDataDBUtils.class);
    
 	public static MongoOperations getMongoOperations() throws Exception {
		
		if( mongoOperation==null){
		
			logger.info("Connecting to db ... ");
			
			MongoClientURI uri = new MongoClientURI(getDatabaseURI());
		    MongoClient client = new MongoClient(uri);
		    
		    mongoOperation = new MongoTemplate(client, getDatabaseName());
	        logger.info("Connected to db : "+  getDatabaseName());
			
		}
	    return mongoOperation;
		
		/*AppConfig appConfig = new AppConfig();
		
		return appConfig.getMongoOperations();*/
	 }
	
	 protected static String getDatabaseName() {
		 
		    try {
		   	    InputStream inputStream = SpringDataDBUtils.class.getClassLoader()
		   	    		.getResourceAsStream(AppConstant.PROPERTIES_FILE);
		   	    properties.load(inputStream);
			
		    } catch (IOException e) {
				
				logger.error("Error:"+e.getMessage());
			}
		  
	        return properties.getProperty(AppConstant.PROPERTIES_DB_NAME);
	  }
	 
	 

	 protected static  String getDatabaseURI() {
			  
		 try {
			 InputStream inputStream = SpringDataDBUtils.class.getClassLoader().getResourceAsStream(AppConstant.PROPERTIES_FILE);
		  	 properties.load(inputStream);

		} catch (IOException e) {
			logger.error("Error:"+e.getMessage());
		}
		
		 String dbURI = properties.getProperty(AppConstant.PROPERTIES_DB_IP);

	  
        logger.info(dbURI);
		 
		 return dbURI;
	}	 
	 
	 public static Properties ssProperties(){
		 try {
			 InputStream inputStream = SpringDataDBUtils.class.getClassLoader()
					 .getResourceAsStream(AppConstant.PROPERTIES_FILE);
			 properties.load(inputStream);

		 } catch (IOException e) {

			 logger.error("Error:"+e.getMessage());
		 }
		  return properties;
	  }
}	