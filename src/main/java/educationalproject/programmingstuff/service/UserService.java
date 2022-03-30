package educationalproject.programmingstuff.service;

import educationalproject.programmingstuff.service.dto.UserResponseDto;

import java.util.List;

public interface UserService {

    List<UserResponseDto> getUsersByName(String name);

    List<UserResponseDto> getAllUsers();
}
