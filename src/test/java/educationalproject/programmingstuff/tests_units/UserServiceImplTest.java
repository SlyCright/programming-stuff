package educationalproject.programmingstuff.tests_units;

import educationalproject.programmingstuff.TestDataCreator;
import educationalproject.programmingstuff.model.User;
import educationalproject.programmingstuff.repositories.UserRepository;
import educationalproject.programmingstuff.servicies.UserServiceImpl;
import educationalproject.programmingstuff.servicies.dto.UserCreateRequestDto;
import educationalproject.programmingstuff.servicies.dto.UserResponseDto;
import educationalproject.programmingstuff.servicies.mappers.UserCreateRequestMapperImpl;
import educationalproject.programmingstuff.servicies.mappers.UserResponseMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @Spy
    UserResponseMapperImpl userResponseMapper; // todo q: Can't spy interface. Even dude on stackoverflow can't

    @Spy
    UserCreateRequestMapperImpl userCreateRequestMapper;

    @InjectMocks
    UserServiceImpl userService;

    private List<User> users = TestDataCreator.getTestUsers();

    @Test
    void givenRequestForAllUsers_whenTriggered_thenSuccess() {

        //Given
        Mockito.when(userRepository.findAll()).thenReturn(users);

        //When
        List<UserResponseDto> userResponseDtos = userService.getAllUsers();

        //Then
        assertEquals(users.size(), userResponseDtos.size());
        Mockito.verify(userRepository, times(1)).findAll();
    }

    @Test
    void givenRequestsForUsersWithCertainName_whenServiceTriggered_thenReturnsUsersWithTheName() {

        //Given
        assert users != null : "Check the test data preparations";
        String testName = users.get(0).getName();
        users = users.stream()
                .filter(u -> u.getName().equals(testName))
                .collect(Collectors.toList());
        Mockito.when(userRepository.getUsersByName(testName)).thenReturn(users);

        //When
        List<UserResponseDto> userResponseDtos = userService.getUsersByName(testName);

        //Then
        assertEquals(users.size(), userResponseDtos.size());
        Mockito.verify(userRepository, times(1)).getUsersByName(testName);
        for (UserResponseDto userResponseDto : userResponseDtos) {
            assertEquals(testName, userResponseDto.getUserName());
        }

    }

    @Test
    void givenNewUserDto_whenServiceTriggered_thenEnsureTheNewUserSentToSave() {

        //Given
        UserCreateRequestDto expectedUser = TestDataCreator.getUserCreateRequestDto();

        //When
        userService.createUser(expectedUser);
        ArgumentCaptor<User> userForCreationCaptor = ArgumentCaptor.forClass(User.class);
        Mockito.verify(userRepository).saveAndFlush(userForCreationCaptor.capture());

        //Then
        User resultUser = userForCreationCaptor.getValue();
        assertEquals(resultUser.getName(), expectedUser.getUserName());
        assertEquals(resultUser.getSurname(), expectedUser.getSurname());

    }

}