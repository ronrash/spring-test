package ronrash.springcontainers.service;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ronrash.springcontainers.TestUtility.TestUtil;
import ronrash.springcontainers.model.entity.PersonEntity;
import ronrash.springcontainers.respository.PersonRepository;
import ronrash.springcontainers.service.impl.PersonServiceImpl;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @InjectMocks
    PersonServiceImpl personService;

    @Mock
    PersonRepository personRepository;

    @Test
    void shouldFilterByAge(){

        // given
        final int age=25;
        List<PersonEntity> personEntityList = TestUtil.getPersonEntityList();
        // when
        Mockito.when(personRepository.findByAge(age)).thenReturn(Optional.of(personEntityList));
        //then
        List<PersonEntity> personEntities = personService.filterByAge(age);

        assertThat(personEntities).hasSize(1);
        assertThat(personEntities.get(0).getName()).isEqualTo("rohan");

    }
}
