package educationalproject.programmingstuff.servicies.mappers;

import educationalproject.programmingstuff.model.User;
import educationalproject.programmingstuff.servicies.dto.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserResponseMapper {

    @Mapping(target = "userName", source = "name")
    UserResponseDto makeUserResponseOf(User user);

    List<UserResponseDto> makeUsersResponseOf(List<User> users);

    @Mapping(source = "userName", target = "name")
    User makeUserEntityOf(UserResponseDto userResponseDto);
}
