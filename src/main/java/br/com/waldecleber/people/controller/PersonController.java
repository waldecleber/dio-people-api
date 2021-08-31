package br.com.waldecleber.people.controller;

import br.com.waldecleber.people.dto.PersonDTO;
import br.com.waldecleber.people.dto.response.MessageResponseDTO;
import br.com.waldecleber.people.entity.Person;
import br.com.waldecleber.people.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

    private PersonService personService;

    PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO person) {
        return personService.createPerson(person);
    }

}
