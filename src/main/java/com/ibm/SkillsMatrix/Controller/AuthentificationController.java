package com.ibm.SkillsMatrix.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cloudant.client.api.Database;
import com.ibm.SkillsMatrix.Bean.Person;
import com.ibm.SkillsMatrix.Business.AuthentificationService;

@RestController

public class AuthentificationController {

	@Autowired
	Database skillsmatrixdb;
	@Autowired
	private AuthentificationService authentificationService;

	@RequestMapping(value="/auth", method = RequestMethod.POST, headers = "Accept=application/json")
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

	
	@RequestMapping(value = "/user")
	public Person users(final HttpServletRequest request, final HttpServletResponse response) {
		final Person authentification = (Person) request.getSession().getAttribute("user");
		return authentification;
	}
}
