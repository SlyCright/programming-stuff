package educationalproject.programmingstuff.data;

import educationalproject.programmingstuff.servicies.dto.StoredItemsResponseDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DtoGenerator {

    public StoredItemsResponseDto.StoredItemsResponseDtoBuilder getStoredItemsResponseDtoBuilder(){

        return StoredItemsResponseDto.builder()
                .itemNumber(DataGenerationCommon.getRandomItemNumber())
                .title(DataGenerationCommon.getRandomUniqueItemTitle())
                .description(DataGenerationCommon.getRandomItemDescription())
                .price(DataGenerationCommon.getRandomItemPrice())
                .quantity(DataGenerationCommon.getRandomItemQuantity());

    }

}
