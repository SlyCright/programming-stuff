package educationalproject.programmingstuff.servicies.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserCreateRequestDto {

    public String userName;

    public String surname;

}
