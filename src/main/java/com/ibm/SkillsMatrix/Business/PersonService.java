package com.ibm.SkillsMatrix.Business;


import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cloudant.client.api.Database;
import com.cloudant.client.api.model.Response;
import com.ibm.SkillsMatrix.Bean.Person;

@Service
public class PersonService {

	@Autowired
	Database skillsmatrixdb;

	

	/*
	 * Cette fonction permet de récupérer toutes les personnes stockées dans la
	 * base de données
	 */
	public List<Person> getAll() {
		List<Person> allDocs = null;

		try {
			allDocs = skillsmatrixdb.getAllDocsRequestBuilder().includeDocs(true).build().getResponse()
					.getDocsAs(Person.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return allDocs;
	}

	/*
	 * Cette fonction permet d'ajouter une nouvelle personnes à la base de
	 * données
	 */
	public Response addPerson(Person person) {
		return skillsmatrixdb.save(person);
	}

	/*
	 * Cette fonction à pour objectif de récupérer une personne par son id
	 */
	public Person getUser(final String Id) {
		Person user = skillsmatrixdb.find(Person.class, Id);
		return user;
	}

	/*
	 * Cette fonction à pour objectif de faire le mise à jour des attributs
	 * d'une personne
	 */
	public Response updateUser(Person person) {
		

		return skillsmatrixdb.update(person);
	}

	
	
	
	

}
