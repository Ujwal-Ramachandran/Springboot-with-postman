package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RequestMapping("Test")
@RestController
public class PersonController {

    private final PersonService personService;

    @RequestMapping("web")
    public String web(){
        return "web.html";
    }

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public void addPerson(@RequestBody Person person){
        personService.addPerson(person);
    }

    @GetMapping
    public List<Person> getAllPeople(){
        return personService.getAllPeople();
    }

    @GetMapping(path="{id}")
    public Person getPersonbyId(@PathVariable("id") UUID id){
        return personService.getPersonbyId(id)
        .orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deletePID(@PathVariable("id") UUID id){
        personService.Deleteperson(id);
    }

    @PutMapping(path = "{id}")
    public void updatePerson(@PathVariable("id") UUID id, @Validated @RequestBody Person personToUpdate){
        personService.updatePerson(id,personToUpdate);
    }
}
