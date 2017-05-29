package com.ibm.SkillsMatrix.Controller;


import java.io.InputStream;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.cloudant.client.api.Database;
import com.cloudant.client.api.model.Params;
import com.cloudant.client.api.model.Response;
import com.ibm.SkillsMatrix.Bean.Person;
import com.ibm.SkillsMatrix.Business.PersonService;

@RestController
@RequestMapping("/person")

public class PersonController {

	@Autowired
	Database skillsmatrixdb;
	@Autowired
	PersonService personService;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<Person> getAll() {
		return personService.getAll();
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public Response saveUser(@RequestBody Person person) {
		return personService.addPerson(person);

	}
	
	@RequestMapping(value="/getOne" ,method=RequestMethod.GET)
	public Person getPerson(@RequestParam String Id){
		return personService.getUser(Id);
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public Response updatePerson(@RequestBody Person person){
		 return personService.updateUser(person);
	}
	
	@RequestMapping(value="/attach" ,method=RequestMethod.GET)
	public void getAttach(@RequestParam String docId){
	skillsmatrixdb.getAttachment(docId, "about-skills-header.png", "42-19ebd2ab13fc68cac6b5e56860709260");
	}
	
	
}
