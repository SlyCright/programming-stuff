package educationalproject.programmingstuff.servicies;

import educationalproject.programmingstuff.model.User;
import educationalproject.programmingstuff.repositories.UserRepository;
import educationalproject.programmingstuff.servicies.dto.UserCreateRequestDto;
import educationalproject.programmingstuff.servicies.dto.UserResponseDto;
import educationalproject.programmingstuff.servicies.mappers.UserCreateRequestMapper;
import educationalproject.programmingstuff.servicies.mappers.UserResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Service
@RequiredArgsConstructor
@Primary
@Validated
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserResponseMapper userResponseMapper;

    private final UserCreateRequestMapper userCreateRequestMapper;

    @Override
    public List<UserResponseDto> getUsersByName(String name) {
        List<User> users = userRepository.getUsersByName(name);
        return userResponseMapper.makeUsersResponseOf(users);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.getUsersByIdIsNotNull();
        return userResponseMapper.makeUsersResponseOf(users);
    }

    @Override
    public UserResponseDto createUser(@Valid UserCreateRequestDto userDto) {

        User userEntityForSaving = userCreateRequestMapper.makeUserEntityOf(userDto);

        User userEntityWhichWasSaved = userRepository.saveAndFlush(userEntityForSaving);

        return userResponseMapper.makeUserResponseOf(userEntityWhichWasSaved);
    }

}
