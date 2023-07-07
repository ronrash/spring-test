package ronrash.springcontainers.model;

import org.junit.jupiter.api.Test;
import ronrash.springcontainers.model.dto.PersonRequest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;

public class PersonRequestTest {

    @Test
    void shouldNotErrorForValidPersonRequest() {
        // Create a valid PersonRequest object
        final PersonRequest personRequest = PersonRequest.builder()
                .name("John Doe")
                .age(25)
                .identityDetail("nic123")
                .build();

        // Validate the PersonRequest object
        Set<ConstraintViolation<PersonRequest>> violations = validate(personRequest);

        // Assert that there are no constraint violations
        assertThat(violations).hasSize(0);
    }

    @Test
    void shouldErrorForMissingName() {
        // Create a PersonRequest object with a missing name
        final PersonRequest personRequest = PersonRequest.builder()
                .age(25)
                .identityDetail("nic123")
                .build();

        // Validate the PersonRequest object
        Set<ConstraintViolation<PersonRequest>> violations = validate(personRequest);

        // Assert that there is a constraint violation for the missing name
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getPropertyPath().toString()).isEqualTo("name");
    }

    @Test
    void shouldErrorForInvalidAge() {
        // Create a PersonRequest object with an invalid age (negative value)
        final PersonRequest personRequest = PersonRequest.builder()
                .name("John Doe")
                .age(-5)
                .identityDetail("nic123")
                .build();

        // Validate the PersonRequest object
        Set<ConstraintViolation<PersonRequest>> violations = validate(personRequest);

        // Assert that there is a constraint violation for the invalid age
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getPropertyPath().toString()).isEqualTo("age");
    }

    // Additional test cases for other constraints...

    private Set<ConstraintViolation<PersonRequest>> validate(PersonRequest personRequest) {
        final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        final Validator validator = validatorFactory.getValidator();
        return validator.validate(personRequest);
    }
}
