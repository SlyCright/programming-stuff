package educationalproject.programmingstuff.servicies.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Value
@Builder
@Valid
public class StoredItemsResponseDto {

    @Min(value = 0, message = "Value must be positive or zero")
    public long itemNumber; // stands for "Артикул" in Russian

    @NotBlank
    String title;

    @NotBlank
    String description;

    @NotNull
    BigDecimal price;

    @Min(value = 0, message = "Value must be positive or zero")
    public int quantity;

}
