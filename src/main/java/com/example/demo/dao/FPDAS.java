package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("fakeDAO")
public class FPDAS implements PersonDAO {

    private static List<Person> DB = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        return DB;
    }

    @Override
    public Optional<Person> selectPersonbyID(UUID id) {
        return DB.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst();
    }

    @Override
    public int delPerson(UUID id) {
        Optional<Person> personMaybe = selectPersonbyID(id);
        if (!personMaybe.isPresent())
            return 0;
        DB.remove((personMaybe.get()));
        return 1;
    }

    @Override
    public int updatePerson(UUID id, Person update) {
        return selectPersonbyID(id)
                .map(person -> {
                    int indexOfP = DB.indexOf((person));
                    if(indexOfP >= 0) {
                        DB.set(indexOfP, new Person(id, update.getName()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }
}
