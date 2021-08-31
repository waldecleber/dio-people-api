package br.com.waldecleber.people.service;

import br.com.waldecleber.people.dto.PersonDTO;
import br.com.waldecleber.people.dto.response.MessageResponseDTO;
import br.com.waldecleber.people.entity.Person;
import br.com.waldecleber.people.repository.PersonRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
}
