package educationalproject.programmingstuff.data;

import educationalproject.programmingstuff.servicies.dto.StoredItemsResponseDto;
import educationalproject.programmingstuff.servicies.dto.UserCreateRequestDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DtoGenerator {

    public StoredItemsResponseDto.StoredItemsResponseDtoBuilder getStoredItemsResponseDtoBuilder() {
        return StoredItemsResponseDto.builder()
                .itemNumber(DataGenerationCommon.getItemNumber())
                .title(DataGenerationCommon.getUniqueItemTitle())
                .description(DataGenerationCommon.getItemDescription())
                .price(DataGenerationCommon.getItemPrice())
                .quantity(DataGenerationCommon.getItemQuantity());
    }

    public UserCreateRequestDto.UserCreateRequestDtoBuilder getUserCreateRequestDtoBuilder() {
        return UserCreateRequestDto.builder()
                .userName(DataGenerationCommon.getName())
                .surname(DataGenerationCommon.getSurname());
    }
}
