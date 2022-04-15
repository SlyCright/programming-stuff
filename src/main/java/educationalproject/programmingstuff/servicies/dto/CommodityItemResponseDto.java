package educationalproject.programmingstuff.servicies.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Value
@Builder
public class CommodityItemResponseDto {

    public long id;

    @NotNull(message = "Item can't be null")
    public ItemResponseDto itemResponseDto;

    @Min(value = 0, message = "Value must be positive or zero")
    public int quantity;

}
