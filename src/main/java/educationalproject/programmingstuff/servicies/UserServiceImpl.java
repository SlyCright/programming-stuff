package educationalproject.programmingstuff.servicies;

import educationalproject.programmingstuff.model.User;
import educationalproject.programmingstuff.repositories.UserRepository;
import educationalproject.programmingstuff.servicies.dto.UserResponseDto;
import educationalproject.programmingstuff.servicies.mappers.UserResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Primary
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserResponseMapper userResponseMapper;

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
    public UserResponseDto saveUser(UserResponseDto userResponseDto) {

        User userEntity = userResponseMapper.makeUserEntityOf(userResponseDto);
        userRepository.saveAndFlush(userEntity);

        String name = userEntity.getName();
        List<User> userResponseDtos = userRepository.getUsersByName(name);
        userEntity = userResponseDtos.get(0);

        return userResponseMapper.makeUserResponseOf(userEntity);
    }

}
