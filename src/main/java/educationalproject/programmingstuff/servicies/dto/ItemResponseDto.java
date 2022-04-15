package educationalproject.programmingstuff.servicies.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Value
@Builder
public class ItemResponseDto {

    public long id;

    @NotBlank
    public String title;

    @NotBlank
    public String description;

    @NotNull
    public BigDecimal price;

}
