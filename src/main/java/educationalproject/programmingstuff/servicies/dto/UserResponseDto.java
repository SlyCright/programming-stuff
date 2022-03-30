package educationalproject.programmingstuff.servicies.dto;

import educationalproject.programmingstuff.model.Order;
import lombok.Value;

import java.util.List;

@Value
public class UserResponseDto {

    Long id;

    String userName;

    String surname;

    List<Order> orders;
}
