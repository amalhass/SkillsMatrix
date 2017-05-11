package com.ibm.SkillsMatrix.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cloudant.client.api.Database;
import com.cloudant.client.api.model.IndexField;
import com.cloudant.client.api.model.IndexField.SortOrder;
import com.cloudant.client.api.model.Response;
import com.ibm.SkillsMatrix.Bean.Person;
import com.ibm.SkillsMatrix.Business.AuthentificationService;

@RestController
@RequestMapping("/person")

public class PersonController {

	@Autowired
	Database skillsmatrixdb;
	@Autowired
	private AuthentificationService authentificationService;



	@RequestMapping(value = "/auth", method = RequestMethod.POST, headers = "Accept=application/json")
	public void authentifier(final HttpServletRequest request, @RequestBody final Person user) {
		final Person authentification = authentificationService.logIn(user.get_id(), user.getPassword());
		if (authentification != null) {
			request.getSession().setAttribute("user", authentification);
		} else {
			request.getSession().removeAttribute("user");

		}
	}
	
	@RequestMapping(value = "/deconnexion", method = RequestMethod.GET)
	public void authentifier(final HttpServletRequest request) {
		request.getSession().removeAttribute("user");
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public Response saveUser(@RequestBody Person person) {
		return skillsmatrixdb.save(person);

	}
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<Person> getAll() {

		List<Person> allDocs = null;

		try {
			allDocs = skillsmatrixdb.getAllDocsRequestBuilder().includeDocs(true).build().getResponse()
					.getDocsAs(Person.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return allDocs;
	}

}
