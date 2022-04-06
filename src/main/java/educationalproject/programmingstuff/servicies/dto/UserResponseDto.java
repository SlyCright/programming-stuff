package educationalproject.programmingstuff.servicies.dto;

import educationalproject.programmingstuff.model.Order;
import lombok.Data;

import java.util.List;

//@Value //Lombock's annotation incompatible with Jackson. Got hassle in tests.
@Data
public class UserResponseDto {

    Long id;

    String userName;

    String surname;

    List<Order> orders;

}
