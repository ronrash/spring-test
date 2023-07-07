package ronrash.springcontainers.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ronrash.springcontainers.TestUtility.TestUtil.getPersonRequest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import ronrash.springcontainers.model.dto.PersonRequest;
import ronrash.springcontainers.service.PersonService;

@WebMvcTest(PersonController.class) // this annotation is used for testing specific controllers
class PersonControllerTest {

    @Autowired
    MockMvc mockMvc; // class provided by spring to simulate http request and response

    @MockBean   // helps us to mock the service class which itself has a lot of dependencies like PersonRepository and they need not be mocked ,so that we can test the controller in isolations
    PersonService personService;

    private static final String BASE_URL = "/api/v1/person";

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("shows the url of the resource created with response status as 201")
    public void shouldReturn201_WhenPersonRequestAdded() throws Exception {
        // given
        PersonRequest personRequest = getPersonRequest(21, "rahul", "nic123");
        String personRequestValue = objectMapper.writeValueAsString(personRequest);
        // when
        when(personService.saveUser(personRequest)).thenReturn(1l);
        // then
        String expectedUrl = "/api/v1/person/1";
        MvcResult mvcResult = mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(personRequestValue)).
                andExpect(status().isCreated()).andReturn();
        String actualUrlOfResourceCreated = mvcResult.getResponse().getHeader("Location");
        assertThat(actualUrlOfResourceCreated).contains(expectedUrl);


    }

    @Test
    @DisplayName("should return 400 due to bad request - name is null")
    void shouldReturn400_WhenIncorrectPersonRequestNameisNotProvided() throws Exception {
        // given
        PersonRequest personRequest = getPersonRequest(21, null, "nic123");
        String personRequestValue = objectMapper.writeValueAsString(personRequest);
        // then
        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(personRequestValue)).
                andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("should return 400 bad request - identityDetail is not provided")
    void shouldReturn400_WhenPersonDetailsNotProvided() throws Exception {
        // given
        PersonRequest personRequest = getPersonRequest(21, "ron", "");
        String personRequestValue = objectMapper.writeValueAsString(personRequest);

        // then
        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(personRequestValue)).
                andExpect(status().isBadRequest());
    }
}