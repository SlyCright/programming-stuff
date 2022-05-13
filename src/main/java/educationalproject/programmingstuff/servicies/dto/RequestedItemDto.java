package educationalproject.programmingstuff.servicies.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.Min;

@Value
@Builder
public class RequestedItemDto {

    long itemId;

    @Min(0)
    int quantity;

}