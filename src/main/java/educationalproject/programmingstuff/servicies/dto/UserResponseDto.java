package educationalproject.programmingstuff.servicies.dto;

import educationalproject.programmingstuff.model.Order;
import lombok.Data;

import java.util.List;

//@Value //Lombock's annotation incompatible with Jackson. Got hassle in tests.
@Data
public class UserResponseDto {

    public Long id;

    public String userName;

    public String surname;

    public List<Order> orders;

}
