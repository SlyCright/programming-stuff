package educationalproject.programmingstuff.service;

import educationalproject.programmingstuff.model.User;
import educationalproject.programmingstuff.repository.UserRepository;
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
    public List<User> getUsersByName(String name) {
        return userRepository.getUsersByName(name);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getUsersByIdIsNotNull();
    }

}
