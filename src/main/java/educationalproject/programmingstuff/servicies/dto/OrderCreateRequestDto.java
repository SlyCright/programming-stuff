package educationalproject.programmingstuff.servicies.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class OrderCreateRequestDto {

     List<RequestedItemDto> requestedItemDtos;

}
