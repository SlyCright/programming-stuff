package educationalproject.programmingstuff.tests_units.servicies;

import educationalproject.programmingstuff.data.EntitiesGenerator;
import educationalproject.programmingstuff.model.CommodityItem;
import educationalproject.programmingstuff.repositories.CommodityItemRepository;
import educationalproject.programmingstuff.servicies.ItemServiceImpl;
import educationalproject.programmingstuff.servicies.dto.StoredItemsResponseDto;
import educationalproject.programmingstuff.servicies.mappers.StoredItemsResponseMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
class ItemServiceImplTest {

    @Spy
    private StoredItemsResponseMapper storedItemsResponseMapper = Mappers.getMapper(StoredItemsResponseMapper.class);

    @Mock
    private CommodityItemRepository commodityItemRepository;

    @InjectMocks
    private ItemServiceImpl itemService;

    @Test
    void givenStoredItems_whenGetStoredItem_thenSuccess() {

        //Given
        List<CommodityItem> givenItems = List.of(
                EntitiesGenerator.getCommodityItemBuilder().build(),
                EntitiesGenerator.getCommodityItemBuilder().build(),
                EntitiesGenerator.getCommodityItemBuilder().build());

        List<StoredItemsResponseDto> expectedDtos = storedItemsResponseMapper.makeStoredItemDtoOf(givenItems);

        Mockito.when(commodityItemRepository.findAll()).thenReturn(givenItems);

        //When
        List<StoredItemsResponseDto> resultDtos = itemService.getStoredItems();

        //Then
        assertThat(resultDtos).isEqualTo(expectedDtos);
    }
}