package ronrash.springcontainers.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ronrash.springcontainers.model.dto.PersonRequest;
import ronrash.springcontainers.model.entity.PersonEntity;
import ronrash.springcontainers.service.PersonService;

@RestController
@RequestMapping("/api/v1/person")
@Validated
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @PostMapping
    public ResponseEntity<String> savePerson(@Valid @RequestBody PersonRequest personRequest) {
        Long personId = personService.saveUser(personRequest);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(personId).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<PersonEntity>> getAllPersonDetails() {
        //
        return ResponseEntity.ok(personService.getAllPersonDetails());
    }

    // this is a dynamic path variable that is extracted from our url request using curly braces'{}'
    //  and passed as method parameter with the help of annotation @PathVariable
    @GetMapping("/{identityDetail}")
    public ResponseEntity<PersonEntity> getPersonByIdentity(@Valid @PathVariable("identityDetail") final String identity) {

        return ResponseEntity.ok(personService.getPersonByIdentitDetails(identity));
    }

    // this is a request param to filter records based on age
    @GetMapping("/age")
    public ResponseEntity<List<PersonEntity>> getAllPersonAboveCertainAge(@Valid @RequestParam("age") final int age) {

        return ResponseEntity.ok(personService.filterByAge(age));
    }

    // updating a person object
    @PutMapping("/{personId}")
    public ResponseEntity updatePerson(@PathVariable Long personId, @RequestBody PersonEntity personEntity) {
        Long updatePersonId = personService.updatePerson(personId, personEntity);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(updatePersonId).toUri();
        return ResponseEntity.ok(location);
    }
}
