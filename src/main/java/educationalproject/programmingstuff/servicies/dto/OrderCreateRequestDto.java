package educationalproject.programmingstuff.servicies.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class OrderCreateRequestDto {

    List<RequestedItemDto> requestedItemDtos;

}
