package com.dlt.practice;

import java.util.HashMap;
import java.util.Map;

public class DataStore {

	private Map<String, Person> personMap = new HashMap<>();

	private static DataStore instance = new DataStore();

	public static DataStore getInstance() {
		return instance;
	}

	private DataStore() {
		// dummy data
		personMap.put("Homer", new Person("Homer", "Homer likes doughnuts", 1985));
		personMap.put("Jerry", new Person("Jerry", "Jerry lives with a cat", 1955));
		personMap.put("Mickey", new Person("Mickey", "Mickey is distantly related to Jerry", 1920));
	}

	public Person getPerson(String name) {
		return personMap.get(name);
	}

	public void putPerson(Person person) {
		personMap.put(person.getName(), person);
	}
}