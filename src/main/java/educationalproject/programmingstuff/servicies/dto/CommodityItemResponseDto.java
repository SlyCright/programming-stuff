package educationalproject.programmingstuff.servicies.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
@Builder
public class CommodityItemResponseDto {

    @NotNull(message = "Id can't be null")
    long id;

    @NotBlank(message = "Empty title is not allowed")
    String title;

    @NotBlank(message = "Empty description is not allowed")
    String description;

    @Min(value=0,message="Value mus be positive or zero")
    int quantity;

}
