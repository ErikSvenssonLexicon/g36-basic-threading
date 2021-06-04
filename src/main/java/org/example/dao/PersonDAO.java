package org.example.dao;

import org.example.model.Gender;
import org.example.model.Person;
import org.example.util.PersonGenerator;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PersonDAO {
    private static final PersonDAO INSTANCE;

    static {
        INSTANCE = new PersonDAO(
                PersonGenerator.getInstance().generate(100000)
        );
    }

    public static PersonDAO getInstance(){
        return INSTANCE;
    }

    private final List<Person> storage;

    private PersonDAO(List<Person> storage) {
        this.storage = storage;
    }

    public Optional<Person> findById(int id){
        return storage.stream()
                .filter(person -> person.getId() == id)
                .findFirst();
    }

    public List<Person> findByGender(Gender gender){
        return storage.stream()
                .filter(person -> person.getGender() == gender)
                .collect(Collectors.toList());
    }




}
