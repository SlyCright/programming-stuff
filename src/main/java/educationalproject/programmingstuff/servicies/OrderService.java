package educationalproject.programmingstuff.servicies;

import educationalproject.programmingstuff.servicies.dto.OrderCreateRequestDto;
import educationalproject.programmingstuff.servicies.dto.OrderResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {

    OrderResponseDto book(OrderCreateRequestDto newOrder);

}
