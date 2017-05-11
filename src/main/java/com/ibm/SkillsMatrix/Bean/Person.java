package com.ibm.SkillsMatrix.Bean;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
public class Person {
	private static final long serialVersionUID = 1L;

	
	private String _id;
	private String _rev;
	private String FirstName;
	private String LastName;
	private String Email;
	private String Password;

	public Person() {

	}
	public Person(String id){
		this._id=id;
	}

	public Person(String id, String rev, String FirstName, String LastName, String Email, String Password) {
		this._id = id;
		this._rev = rev;
		this.FirstName = FirstName;
		this.LastName = LastName;
		this.Email = Email;
		this.Password = Password;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String get_rev() {
		return _rev;
	}

	public void set_rev(String _rev) {
		this._rev = _rev;
	}

	public String getFirstName() {
		return FirstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

}
