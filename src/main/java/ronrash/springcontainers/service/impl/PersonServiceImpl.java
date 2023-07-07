package ronrash.springcontainers.service.impl;

import static java.lang.String.format;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ronrash.springcontainers.exception.PersonNotFoundException;
import ronrash.springcontainers.model.dto.PersonRequest;
import ronrash.springcontainers.model.entity.PersonEntity;
import ronrash.springcontainers.respository.PersonRepository;
import ronrash.springcontainers.service.PersonService;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Override
    public Long saveUser(final PersonRequest personRequest) {
        PersonEntity personEntity = PersonEntity.builder()
                .name(personRequest.getName())
                .age(personRequest.getAge())
                .identityDetail(personRequest.getIdentityDetail()).build();
        PersonEntity save = personRepository.save(personEntity);
        return save.getPersonId();
    }

    @Override
    public PersonEntity getPersonByIdentitDetails(final String identity) {
        return personRepository
                .findByIdentityDetail(identity)
                .orElseThrow(() -> new PersonNotFoundException(format("No mathcing person")));
    }

    @Override
    public List<PersonEntity> getAllPersonDetails() {
        return personRepository.findAll();
    }

    @Override
    public List<PersonEntity> filterByAge(final int age) {
        List<PersonEntity> personEntities = personRepository.findByAge(age)
                .orElseThrow(() -> new PersonNotFoundException("No matching person"));
        List<PersonEntity> entities =
                personEntities.stream().filter(personEntity -> personEntity.getAge() <= 25).collect(Collectors.toList());


        return entities;
    }

    @Override
    public Long updatePerson(final Long personId, final PersonEntity personEntity) {
        PersonEntity actualEntity = personRepository.findById(personId).orElseThrow(() -> new PersonNotFoundException("Person does not exist"));

        actualEntity.setPersonId(actualEntity.getPersonId());
        actualEntity.setAge(personEntity.getAge());
        actualEntity.setName(personEntity.getName());
        actualEntity.setIdentityDetail(personEntity.getIdentityDetail());

        return personRepository.save(actualEntity).getPersonId();
    }
}
