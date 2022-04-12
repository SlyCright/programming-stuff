package educationalproject.programmingstuff.tests_units;

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
    void givenInvocationOfGetAllCommodityItems_whenInvoked_whenSucceed() {

        //Given
        List<CommodityItem> expectedCommodities = List.of(
                EntitiesGenerator.getCommodityItemBuilder().build(),
                EntitiesGenerator.getCommodityItemBuilder().build(),
                EntitiesGenerator.getCommodityItemBuilder().build());

        Mockito.when(commodityItemRepository.findAll()).thenReturn(expectedCommodities);

        //When
        List<CommodityItemResponseDto> resultCommodities = commodityItemService.getAllCommodityItems();

        //Then
        assertThat(resultCommodities.size()).isEqualTo(expectedCommodities.size());

        for (CommodityItemResponseDto result: resultCommodities) {

            int index=resultCommodities.indexOf(result);
            CommodityItem expect = expectedCommodities.get(index);
            assertThat(result.getItem()).isEqualTo(expect.getItem());
            assertThat(result.getQuantity()).isEqualTo(expect.getQuantity());
        }
 
    }
}