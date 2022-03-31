package educationalproject.programmingstuff.servicies.mappers;

import educationalproject.programmingstuff.model.User;
import educationalproject.programmingstuff.servicies.dto.UserCreateRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserCreateRequestMapper {

    @Mapping(source = "userName", target = "name")
    User makeUserEntityOf(UserCreateRequestDto user);

}
