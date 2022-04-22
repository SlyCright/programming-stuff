package educationalproject.programmingstuff.tests_units.servicies;

import educationalproject.programmingstuff.data.EntitiesGenerator;
import educationalproject.programmingstuff.model.CommodityItem;
import educationalproject.programmingstuff.servicies.dto.StoredItemsResponseDto;
import educationalproject.programmingstuff.servicies.mappers.StoredItemsMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

class StoredItemsMapperTest {

    StoredItemsMapper storedItemsMapper = Mappers.getMapper(StoredItemsMapper.class);

    @Test
    void givenCommodityItem_thenMakeStoredItemDto_thenSuccess() {

        //Given
        CommodityItem given = EntitiesGenerator.getCommodityItemBuilder().build();

        //When
        StoredItemsResponseDto resultDto = storedItemsMapper.makeStoredItemDtoOf(given);

        //Then
            assertThat(resultDto.getItemNumber()).isEqualTo(given.getId());
            assertThat(resultDto.getTitle()).isEqualTo(given.getItem().getTitle());
            assertThat(resultDto.getDescription()).isEqualTo(given.getItem().getDescription());
            assertThat(resultDto.getPrice()).isEqualTo(given.getItem().getPrice());
            assertThat(resultDto.getQuantity()).isEqualTo(given.getQuantity());
    }

}