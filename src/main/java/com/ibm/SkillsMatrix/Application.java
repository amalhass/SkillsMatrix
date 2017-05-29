package com.ibm.SkillsMatrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;

@SpringBootApplication
public class Application {
	 public static void main(String[] args) {
	        SpringApplication.run(Application.class, args);
	        
	    }
	 
	 @Bean
	 public Database skillsmatrixdb(CloudantClient cloudant) {
	 	return cloudant.database("skillsmatrixdb", true);
	 }
}
