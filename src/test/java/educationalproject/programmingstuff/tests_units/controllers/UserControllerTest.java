package educationalproject.programmingstuff.tests_units.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import educationalproject.programmingstuff.controllers.UserController;
import educationalproject.programmingstuff.servicies.UserServiceImpl;
import educationalproject.programmingstuff.servicies.dto.UserCreateRequestDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @MockBean
    UserServiceImpl userService;

    @Autowired
    MockMvc mockMvc;

    final ObjectMapper jacksonMapper = new ObjectMapper();

    @Test
    void givenUserRequestWithName_whenResponseUsers_thenUserServiceInvokeAppropriateMethod() throws Exception {

        //Given

        //When
        mockMvc.perform(get("/users").param("name", "John"));

        //Then
        Mockito.verify(userService).getUsersByName("John");
        Mockito.verifyNoMoreInteractions(userService);

    }

    @Test
    void givenUserRequestWithNoName_whenResponseUsers_thenUserServiceInvokeAppropriateMethod() throws Exception {

        //Given

        //When
        mockMvc.perform(get("/users"));

        //Then
        Mockito.verify(userService).getAllUsers();
        Mockito.verifyNoMoreInteractions(userService);

    }

    @Test
    void givenCreateUserPost_whenCreateUser_thenUserServiceInvokeAppropriateMethod() throws Exception {

        //Given

        //When
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/users")
                        .content(jacksonMapper.writeValueAsString(
                                UserCreateRequestDto
                                        .builder()
                                        .userName("userName")
                                        .surname("Surname")
                                        .build()))
                        .contentType(MediaType.APPLICATION_JSON));

        //Then
        Mockito.verify(userService).createUser(UserCreateRequestDto.builder()
                .userName("userName")
                .surname("Surname")
                .build());

    }

}