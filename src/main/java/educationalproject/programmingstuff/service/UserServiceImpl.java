package educationalproject.programmingstuff.service;

import educationalproject.programmingstuff.model.User;
import educationalproject.programmingstuff.repository.UserRepository;
import educationalproject.programmingstuff.service.dto.UserResponseDto;
import educationalproject.programmingstuff.service.mappers.UserResponseMapper;
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
        List<User> users=userRepository.getUsersByName(name);
        return userResponseMapper.makeUsersResponseOf(users);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> users=userRepository.getUsersByIdIsNotNull();
        return userResponseMapper.makeUsersResponseOf(users);
    }

}
