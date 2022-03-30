package educationalproject.programmingstuff.servicies;

import educationalproject.programmingstuff.servicies.dto.UserResponseDto;

import java.util.List;

public interface UserService {

    List<UserResponseDto> getUsersByName(String name);

    List<UserResponseDto> getAllUsers();

    UserResponseDto saveUser(UserResponseDto user);
}
