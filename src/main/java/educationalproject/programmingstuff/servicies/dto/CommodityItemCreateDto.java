package educationalproject.programmingstuff.servicies.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Value
@Builder
public class CommodityItemCreateDto {

    @NotBlank(message = "Empty title is not allowed")
    String title;

    @NotBlank(message = "Empty description is not allowed")
    String description;

    @Min(value=0,message="Value mus be positive or zero")
    int quantity;

}
