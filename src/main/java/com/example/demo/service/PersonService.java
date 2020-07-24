package com.example.demo.service;

import com.example.demo.dao.PersonDAO;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class PersonService {

    private final PersonDAO persondao;

    @Autowired
    public PersonService(@Qualifier("fakeDAO") PersonDAO persondao) {
        this.persondao = persondao;
    }

    public int addPerson(Person person){
        return persondao.insertPerson(person);
    }

    public List<Person> getAllPeople(){
        return persondao.selectAllPeople();
    }

    public Optional<Person> getPersonbyId(UUID id){
        return persondao.selectPersonbyID(id);
    }

    public int Deleteperson (UUID id){
        return persondao.delPerson(id);
    }

    public int updatePerson(UUID id, Person newPerson){
        return persondao.updatePerson(id, newPerson);

    }
}
