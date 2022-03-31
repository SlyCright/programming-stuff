package educationalproject.programmingstuff.servicies;

import educationalproject.programmingstuff.servicies.dto.UserCreateRequestDto;
import educationalproject.programmingstuff.servicies.dto.UserResponseDto;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Validated
public interface UserService {

    List<UserResponseDto> getUsersByName(String name);

    List<UserResponseDto> getAllUsers();

    UserResponseDto createUser(@Valid UserCreateRequestDto user);
}
