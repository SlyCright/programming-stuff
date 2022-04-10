package educationalproject.programmingstuff.servicies.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserCreateRequestDto {

    String userName;

    String surname;

}
