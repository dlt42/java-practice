package com.dlt.practice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Person {
	private String name;
	private String about;
	private int birthYear;

	public Person(String name, String about, int birthYear) {
		this.name = name;
		this.about = about;
		this.birthYear = birthYear;
	}

	public String getName() {
		return name;
	}

	public String getAbout() {
		return about;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public String toJSOString() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String json = mapper.writeValueAsString(this);
			return json;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw e;
		}
	}
}