package com.ibm.SkillsMatrix.Bean;

import java.io.InputStream;
import java.sql.Date;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.cloudant.client.api.model.Attachment;

@EntityScan
public class Person {

	private String _id;
	private String _rev;
	private String FirstName;
	private String LastName;
	private String Email;
	private String Password;
	private String Job;
	private String Role;
	private String Country;
	private String Site;
	private String IbmId;
	private String Brand;
	private String ProjectName;
	private Date EndOfProject;
	private String UseRate;
	private InputStream _attachments;

	public Person() {

	}

	public Person(String id) {
		this._id = id;
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

	public String getJob() {
		return Job;
	}

	public void setJob(String job) {
		Job = job;
	}

	public String getRole() {
		return Role;
	}

	public void setRole(String role) {
		Role = role;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

	public String getSite() {
		return Site;
	}

	public void setSite(String site) {
		Site = site;
	}

	public String getIbmId() {
		return IbmId;
	}

	public void setIbmId(String ibmId) {
		IbmId = ibmId;
	}

	public String getBrand() {
		return Brand;
	}

	public void setBrand(String brand) {
		Brand = brand;
	}

	public String getProjectName() {
		return ProjectName;
	}

	public void setProjectName(String projectName) {
		ProjectName = projectName;
	}

	public Date getEndOfProject() {
		return EndOfProject;
	}

	public void setEndOfProject(Date endOfProject) {
		EndOfProject = endOfProject;
	}

	public String getUseRate() {
		return UseRate;
	}

	public void setUseRate(String useRate) {
		UseRate = useRate;
	}

	public InputStream get_attachment() {
		return _attachments;
	}

	public void set_attachement(InputStream _attachment) {
		this._attachments = _attachment;
	}

}
