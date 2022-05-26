package educationalproject.programmingstuff.servicies.mappers;

import educationalproject.programmingstuff.model.Order;
import educationalproject.programmingstuff.servicies.dto.OrderResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderResponseMapper {

    OrderResponseDto makeOrderResponseOf(Order order);

}
