package educationalproject.programmingstuff.service;

import educationalproject.programmingstuff.model.User;
import educationalproject.programmingstuff.repository.UserRepository;
import educationalproject.programmingstuff.service.dto.UserResponseDto;
import educationalproject.programmingstuff.service.mappers.UserResponseMapperImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Primary
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserResponseDto> getUsersByName(String name) {
        List<User> users=userRepository.getUsersByName(name);
        UserResponseMapperImpl userResponseMapper=new UserResponseMapperImpl(); // eah, that's probably wrong. I didn't get it yet how to avoid instancing
        return userResponseMapper.makeUsersResponseOf(users);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> users=userRepository.getUsersByIdIsNotNull();
        UserResponseMapperImpl userResponseMapper=new UserResponseMapperImpl();
        return userResponseMapper.makeUsersResponseOf(users);
    }

}
