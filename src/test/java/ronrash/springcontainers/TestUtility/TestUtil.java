package ronrash.springcontainers.TestUtility;

import static java.util.Arrays.asList;

import java.util.List;

import ronrash.springcontainers.model.dto.PersonRequest;
import ronrash.springcontainers.model.entity.PersonEntity;

public class TestUtil {

    public static PersonEntity getPersonEntity(int age, String name, String identityDetails) {
        return PersonEntity.builder()
                .identityDetail(identityDetails)
                .age(age)
                .name(name)
                .build();
    }

    public static List<PersonEntity> getPersonEntityList() {
        PersonEntity rohit = PersonEntity.builder()
                .identityDetail("123")
                .age(28)
                .name("rohit")
                .build();
        PersonEntity rohan = PersonEntity.builder()
                .identityDetail("23")
                .age(22)
                .name("rohan")
                .build();
        return asList(rohan, rohit);
    }

    public static PersonRequest getPersonRequest(int age, String name, String identityDetails) {
        return PersonRequest.builder()
                .identityDetail(identityDetails)
                .age(age)
                .name(name)
                .build();
    }

    public static List<PersonRequest> getPersonRequestList() {
        PersonRequest rohit = PersonRequest.builder()
                .identityDetail("123")
                .age(28)
                .name("rohit")
                .build();
        PersonRequest rohan = PersonRequest.builder()
                .identityDetail("23")
                .age(22)
                .name("rohan")
                .build();
        return asList(rohan, rohit);
    }
}
