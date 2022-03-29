package educationalproject.programmingstuff.service.mappers;

import educationalproject.programmingstuff.model.User;
import educationalproject.programmingstuff.service.dto.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserResponseMapper {

    @Mapping(target = "userName", source = "name")
    UserResponseDto makeUserResponseOf(User user);

    @Mapping(target = "userName", source = "name")
    List<UserResponseDto> makeUsersResponseOf(List<User> users);

}
