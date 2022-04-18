package educationalproject.programmingstuff.tests_units.servicies;

import educationalproject.programmingstuff.data.EntitiesGenerator;
import educationalproject.programmingstuff.model.CommodityItem;
import educationalproject.programmingstuff.repositories.CommodityItemRepository;
import educationalproject.programmingstuff.servicies.CommodityItemServiceImpl;
import educationalproject.programmingstuff.servicies.dto.CommodityItemResponseDto;
import educationalproject.programmingstuff.servicies.mappers.CommodityItemResponseMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class CommodityItemServiceImplTest {

    @Mock
    CommodityItemRepository commodityItemRepository;

    @Spy
    CommodityItemResponseMapper commodityItemResponseMapper = Mappers.getMapper(CommodityItemResponseMapper.class);

    @Spy
    @InjectMocks
    CommodityItemServiceImpl commodityItemService;

    @Test
    void givenCommodityItems_whenGetAllCommodityItems_thenSuccess() {

        //Given
        List<CommodityItem> givenCommodities = List.of(
                EntitiesGenerator.getCommodityItemBuilder().build(),
                EntitiesGenerator.getCommodityItemBuilder().build(),
                EntitiesGenerator.getCommodityItemBuilder().build());

        List<CommodityItemResponseDto> expectedCommodities = commodityItemResponseMapper
                .makeCommodityItemResponseOf(givenCommodities);

        Mockito.when(commodityItemRepository.findAll()).thenReturn(givenCommodities);

        //When
        List<CommodityItemResponseDto> resultCommodities = commodityItemService.getAllCommodityItems();

        //Then
        assertThat(resultCommodities).isEqualTo(expectedCommodities);
        assertThat(resultCommodities.get(0).getItemResponseDto()).isNotNull();

    }

}