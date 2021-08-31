package br.com.waldecleber.people.service;

import br.com.waldecleber.people.dto.PersonDTO;
import br.com.waldecleber.people.dto.response.MessageResponseDTO;
import br.com.waldecleber.people.entity.Person;
import br.com.waldecleber.people.exception.PersonNotFoundException;
import br.com.waldecleber.people.repository.PersonRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private PersonRepository personRepository;

    private ModelMapper mapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);

    public PersonService(PersonRepository personRepository, ModelMapper mapper) {
        this.personRepository = personRepository;
        this.mapper = mapper;
    }

    public MessageResponseDTO createPerson(PersonDTO personDTO) {
        Person person = mapper.map(personDTO, Person.class);
        personRepository.save(person);

        LOGGER.info(String.format("Created person with success", person.getId()));
        return MessageResponseDTO.builder()
                .message("Created person with success")
                .build();
    }

    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(p -> mapper.map(p, PersonDTO.class))
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = verifyIfPersonExists(id);

        return mapper.map(person, PersonDTO.class);
    }

    private Person verifyIfPersonExists(Long id) throws PersonNotFoundException {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }


    public void deleteById(Long id) throws PersonNotFoundException {
        verifyIfPersonExists(id);
        personRepository.deleteById(id);
        LOGGER.info(String.format("Deleted person with success", id));
    }

    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyIfPersonExists(id);

        Person person = mapper.map(personDTO, Person.class);
        personRepository.save(person);
        LOGGER.info(String.format("Updated person with success", person.getId()));

        return MessageResponseDTO.builder()
                .message("Updated person with success")
                .build();
    }
}
