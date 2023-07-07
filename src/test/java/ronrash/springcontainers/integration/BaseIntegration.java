package ronrash.springcontainers.integration;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import ronrash.springcontainers.TestUtility.TestUtil;
import ronrash.springcontainers.model.entity.PersonEntity;
import ronrash.springcontainers.respository.PersonRepository;

@SpringBootTest
@AutoConfigureMockMvc
public abstract class BaseIntegration {

    @Autowired
    protected PersonRepository personRepository;

    @Autowired
    protected MockMvc mockMvc;

    protected ObjectMapper objectMapper = new ObjectMapper();

    protected static final String BASE_URL = "/api/v1/person";

    @Container
    protected static PostgreSQLContainer postgreSQLContainer =
            new PostgreSQLContainer("postgres")
                    .withDatabaseName("mytestcontainer")
                    .withUsername("postgres")
                    .withPassword("postgres");

    @BeforeAll
    static void beforeAll() {
        postgreSQLContainer.start();

    }

    @BeforeEach
    void initialSetup() {
        createData();
    }

    @AfterEach
    void tearDown() {
        deleteAll();
    }

    private void createData() {
        List<PersonEntity> personEntityList = TestUtil.getPersonEntityList();
        personRepository.saveAll(personEntityList);
    }

    private void deleteAll() {
        personRepository.deleteAll();
    }

    @AfterAll
    static void afterAll() {
        postgreSQLContainer.stop();

    }
}
