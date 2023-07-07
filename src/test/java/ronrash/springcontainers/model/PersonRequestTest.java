package ronrash.springcontainers.model;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import ronrash.springcontainers.model.dto.PersonRequest;

public class PersonRequestTest {


    private Set<ConstraintViolation<PersonRequest>> validate(PersonRequest creditCardRequest)
    {
        final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        final Validator validator = validatorFactory.getValidator();
        return validator.validate(creditCardRequest);
    }
}
