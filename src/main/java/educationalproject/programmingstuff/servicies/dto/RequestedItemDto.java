package educationalproject.programmingstuff.servicies.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Min;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor

public class RequestedItemDto {

    long itemId;

    @Min(0)
    int quantity;

}