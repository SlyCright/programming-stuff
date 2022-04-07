package educationalproject.programmingstuff.tests_units;

import com.fasterxml.jackson.databind.ObjectMapper;
import educationalproject.programmingstuff.servicies.UserServiceImpl;
import educationalproject.programmingstuff.servicies.dto.UserCreateRequestDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest
@AutoConfigureMockMvc
@AutoConfigureWebMvc
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
        Mockito.verify(userService).getUsersByName(any());
        Mockito.verify(userService, times(0)).getAllUsers();
        Mockito.verify(userService, times(0)).createUser(any());

    }

    @Test
    void givenUserRequestWithNoName_whenResponseUsers_thenUserServiceInvokeAppropriateMethod() throws Exception {

        //Given

        //When
        mockMvc.perform(get("/users"));

        //Then
        Mockito.verify(userService, times(0)).getUsersByName(any());
        Mockito.verify(userService).getAllUsers();
        Mockito.verify(userService, times(0)).createUser(any());

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
        Mockito.verify(userService, times(0)).getUsersByName(any());
        Mockito.verify(userService, times(0)).getAllUsers();
        Mockito.verify(userService).createUser(any());

    }

}