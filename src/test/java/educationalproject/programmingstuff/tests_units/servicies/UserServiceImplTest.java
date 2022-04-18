package educationalproject.programmingstuff.tests_units.servicies;

import educationalproject.programmingstuff.model.User;
import educationalproject.programmingstuff.repositories.UserRepository;
import educationalproject.programmingstuff.servicies.UserServiceImpl;
import educationalproject.programmingstuff.servicies.dto.UserCreateRequestDto;
import educationalproject.programmingstuff.servicies.dto.UserResponseDto;
import educationalproject.programmingstuff.servicies.mappers.UserCreateRequestMapper;
import educationalproject.programmingstuff.servicies.mappers.UserResponseMapper;
import educationalproject.programmingstuff.test_data_prep.TestDataFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Spy
    private UserResponseMapper userResponseMapper = Mappers.getMapper(UserResponseMapper.class);

    @Spy
    private UserCreateRequestMapper userCreateRequestMapper = Mappers.getMapper(UserCreateRequestMapper.class);

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void whenGetAllUsers_thenSuccess() {

        //Given
        List<User> users = TestDataFactory.getUsersListBuilderWithDefaultUsers().build();
        Mockito.when(userRepository.findAll()).thenReturn(users);

        //When
        List<UserResponseDto> userResponseDtos = userService.getAllUsers();

        //Then
        assertThat(userResponseDtos.size()).isEqualTo(users.size());
        Mockito.verify(userRepository).findAll();
    }

    @Test
    void givenUserName_whenGetUsersByName_thenSuccess() {

        //Given
        List<User> users = List.of(User
                .builder()
                .name("John")
                .surname("Smith")
                .build());

        Mockito.when(userRepository.getUsersByName("John")).thenReturn(users);

        //When
        List<UserResponseDto> userResponseDtos = userService.getUsersByName("John");

        //Then
        assertThat(userResponseDtos.size()).isEqualTo(1);
        assertThat(userResponseDtos.get(0).getUserName()).isEqualTo("John");

    }

    @Test
    void givenUserCreateRequestDto_whenCreateUser_thenSuccess() {

        //Given
        ArgumentCaptor<User> userForCreationCaptor = ArgumentCaptor.forClass(User.class);
        UserCreateRequestDto expectedUser = UserCreateRequestDto
                .builder()
                .userName("Sasha")
                .surname("Grey")
                .build();

        //When
        userService.createUser(expectedUser);

        //Then
        Mockito.verify(userRepository).saveAndFlush(userForCreationCaptor.capture());
        User resultUser = userForCreationCaptor.getValue();
        assertThat(expectedUser.getUserName()).isEqualTo(resultUser.getName());
        assertThat(expectedUser.getSurname()).isEqualTo(resultUser.getSurname());
    }

}