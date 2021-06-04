package org.example.util;


import org.example.model.Gender;
import org.example.model.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonGenerator {
	
	private static final PersonGenerator INSTANCE;
	
	private PersonGenerator() {		
	}
	
	static {
		INSTANCE = new PersonGenerator();
	}
	
	public static PersonGenerator getInstance() {
		return INSTANCE;
	}
	
	public List<Person> generate(int amount){
		List<Person> persons = new ArrayList<>();
		for(int i=0 ; i<amount; i++) {
			Gender gender = getRandomGender();
			String firstName;
			String lastName;
			int age;
			
			if(gender == Gender.FEMALE) {
				firstName = RandomNameService.getInstance().getRandomFemaleFirstName();
			}else {
				firstName = RandomNameService.getInstance().getRandomMaleFirstName();
			}
			
			lastName = RandomNameService.getInstance().getRandomLastName();
			
			age = RandomNumberGenerator.getInstance().getRandomInt(0, 65);
			persons.add(new Person(firstName, lastName, age, gender));
		}
		return persons;
		
	}
	
	private Gender getRandomGender() {
		boolean isFemale = RandomNumberGenerator.getInstance().getRandomBoolean();		
		return isFemale ? Gender.FEMALE : Gender.MALE;
	}
	

}
