 package com.ibm.SkillsMatrix.Business;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudant.client.api.Database;
import com.ibm.SkillsMatrix.Bean.Person;

@Service
public class AuthentificationService {

	@Autowired
	Database skillsmatrixdb;
	
	public Person logIn(final String login,final String pwd){
		Person user=skillsmatrixdb.find(Person.class,login);
		if(user!=null && user.getPassword().equals(pwd)){
		
		return user;
	}
		return null;
	}
}
