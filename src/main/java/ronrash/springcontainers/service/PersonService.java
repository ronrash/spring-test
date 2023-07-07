package ronrash.springcontainers.service;

import java.util.List;

import ronrash.springcontainers.model.dto.PersonRequest;
import ronrash.springcontainers.model.entity.PersonEntity;

public interface PersonService {
    Long saveUser(PersonRequest personRequest);

    PersonEntity getPersonByIdentitDetails(String identity);

    List<PersonEntity> getAllPersonDetails();

    List<PersonEntity> filterByAge(int age);

    Long updatePerson(Long personId, PersonEntity personEntity);
}
