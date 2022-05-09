package educationalproject.programmingstuff.servicies.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
@Builder
public class UserCreateRequestDto {

    @NotBlank
    public String userName;

    @NotBlank
    public String surname;

}
