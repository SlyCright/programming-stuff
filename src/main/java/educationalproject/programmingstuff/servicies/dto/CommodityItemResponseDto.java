package educationalproject.programmingstuff.servicies.dto;

import educationalproject.programmingstuff.model.Item;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Value
@Builder
public class CommodityItemResponseDto {

    @NotNull(message = "Id can't be null")
    long id;

    @NotNull(message = "Item can't be null")
    Item item; // TODO: 12.04.2022 Here should be DTO not Entity

    @Min(value = 0, message = "Value must be positive or zero")
    int quantity;

}
