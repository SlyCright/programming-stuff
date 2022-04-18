package educationalproject.programmingstuff.tests_units.servicies;

import educationalproject.programmingstuff.data.DtoGenerator;
import educationalproject.programmingstuff.data.EntitiesGenerator;
import educationalproject.programmingstuff.model.CommodityItem;
import educationalproject.programmingstuff.servicies.dto.StoredItemsResponseDto;
import educationalproject.programmingstuff.servicies.mappers.StoredItemsResponseMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class StoredItemsResponseMapperTest {

    StoredItemsResponseMapper storedItemsResponseMapper = Mappers.getMapper(StoredItemsResponseMapper.class);

    @Test
    void givenCommodityItems_thenMakeStoredItemDto_thenSuccess() {

        //Given
        List<CommodityItem> given = List.of(
                EntitiesGenerator.getCommodityItemBuilder().build(),
                EntitiesGenerator.getCommodityItemBuilder().build(),
                EntitiesGenerator.getCommodityItemBuilder().build());

        //When
        List<StoredItemsResponseDto> resultDtos = storedItemsResponseMapper.makeStoredItemDtoOf(given);

        //Then
        for (StoredItemsResponseDto resultDto : resultDtos) {
            int index = resultDtos.indexOf(resultDto);
            CommodityItem expectItem = given.get(index);

            assertThat(resultDto.getItemNumber()).isEqualTo(expectItem.getId());
            assertThat(resultDto.getTitle()).isEqualTo(expectItem.getItem().getTitle());
            assertThat(resultDto.getDescription()).isEqualTo(expectItem.getItem().getDescription());
            assertThat(resultDto.getPrice()).isEqualTo(expectItem.getItem().getPrice());
            assertThat(resultDto.getQuantity()).isEqualTo(expectItem.getQuantity());

        }

    }
}