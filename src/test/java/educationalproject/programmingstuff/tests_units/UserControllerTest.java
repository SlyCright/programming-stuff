package educationalproject.programmingstuff.tests_units;

import educationalproject.programmingstuff.TestDataCreator;
import educationalproject.programmingstuff.controller.UserController;
import educationalproject.programmingstuff.servicies.UserServiceImpl;
import educationalproject.programmingstuff.servicies.dto.UserCreateRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

//@WebMvcTest doesn't work fo me and for dude from stackoverflow as well (look down of the page): https://stackoverflow.com/questions/48078044/webmvctest-fails-with-java-lang-illegalstateexception-failed-to-load-applicati
@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserControllerTest {

    @MockBean
    UserServiceImpl userService;

    @Autowired
    UserController userController;

    @Test
    void givenUserRequestWithName_whenControllerPassIt_thenUserServiceInvokeAppropriateMethod() {

        //Given
        String testName = "testName";

        //When
        userController.responseUsers(testName);

        //Then
        Mockito.verify(userService, times(1)).getUsersByName(any());
        Mockito.verify(userService, times(0)).getAllUsers();
        Mockito.verify(userService, times(0)).createUser(any());

    }

    @Test
    void givenUserRequestWithNoName_whenControllerPassIt_thenUserServiceInvokeAppropriateMethod() {

        //Given
        String testName = null;

        //When
        userController.responseUsers(testName);

        //Then
        Mockito.verify(userService, times(0)).getUsersByName(any());
        Mockito.verify(userService, times(1)).getAllUsers();
        Mockito.verify(userService, times(0)).createUser(any());

    }

    @Test
    void givenCreateUserPost_whenControllerPassIt_thenUserServiceInvokeAppropriateMethod() {

        //Given
        String testName = null;

        //When
        UserCreateRequestDto userForCreation = TestDataCreator.getUserCreateRequestDto();
        userController.createUser(userForCreation);

        //Then
        Mockito.verify(userService, times(0)).getUsersByName(any());
        Mockito.verify(userService, times(0)).getAllUsers();
        Mockito.verify(userService, times(1)).createUser(any());

    }

}