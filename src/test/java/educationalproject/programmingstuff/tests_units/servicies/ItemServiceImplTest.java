package educationalproject.programmingstuff.tests_units.servicies;

import educationalproject.programmingstuff.data.EntitiesGenerator;
import educationalproject.programmingstuff.model.CommodityItem;
import educationalproject.programmingstuff.model.Item;
import educationalproject.programmingstuff.repositories.CommodityItemRepository;
import educationalproject.programmingstuff.repositories.ItemRepository;
import educationalproject.programmingstuff.servicies.ItemServiceImpl;
import educationalproject.programmingstuff.servicies.dto.StoredItemsResponseDto;
import educationalproject.programmingstuff.servicies.mappers.StoredItemsMapper;
import educationalproject.programmingstuff.servicies.mappers.StoredItemsResponseMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
class ItemServiceImplTest {

    @Spy
    private StoredItemsResponseMapper storedItemsResponseMapper = Mappers.getMapper(StoredItemsResponseMapper.class);

    @Spy
    private StoredItemsMapper storedItemsMapper = Mappers.getMapper(StoredItemsMapper.class);

    @Mock
    private CommodityItemRepository commodityItemRepository;

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemServiceImpl itemService;

    @Test
    void givenStoredItems_whenGetStoredItem_thenSuccess() {

        //Given
        List<Item> givenItems = List.of(
                EntitiesGenerator.getItemBuilder().id(0).build(),
                EntitiesGenerator.getItemBuilder().id(1).build(),
                EntitiesGenerator.getItemBuilder().id(2).build());
        List<CommodityItem> givenCommodityItems = List.of(
                EntitiesGenerator.getCommodityItemBuilder(givenItems.get(0)).id(0).build(),
                EntitiesGenerator.getCommodityItemBuilder(givenItems.get(1)).id(1).build(),
                EntitiesGenerator.getCommodityItemBuilder(givenItems.get(2)).id(2).build());
        List<StoredItemsResponseDto> expectedDtos = storedItemsMapper.makeStoredItemDtoOf(givenCommodityItems);

        Mockito.when(commodityItemRepository.findAll()).thenReturn(givenCommodityItems);
        Mockito.when(itemRepository.findAll()).thenReturn(givenItems);

        //When
        List<StoredItemsResponseDto> resultDtos = itemService.getStoredItems();

        //Then
        assertThat(resultDtos).isEqualTo(expectedDtos);
    }
}