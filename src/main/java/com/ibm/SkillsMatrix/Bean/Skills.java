package com.ibm.SkillsMatrix.Bean;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
public class Skills {

	private String Name;
	private String Level;

	public Skills() {

	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getLevel() {
		return Level;
	}

	public void setLevel(String Level) {
		this.Level = Level;
	}
}
