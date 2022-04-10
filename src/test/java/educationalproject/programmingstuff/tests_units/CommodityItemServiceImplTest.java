package educationalproject.programmingstuff.tests_units;

import educationalproject.programmingstuff.data.CommodityItemGenerator;
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
    CommodityItemServiceImpl commodityItemServiceImpl; // TODO: 10.04.2022 q: should be interface here?

    @Test
    void givenInvocationOfGetAllCommodityItems_whenInvoked_whenSucceed() {

        //Given
        List<CommodityItem> expectedCommodities = List.of(
                CommodityItemGenerator.getCommodityItemBuilder().build(),
                CommodityItemGenerator.getCommodityItemBuilder().build(),
                CommodityItemGenerator.getCommodityItemBuilder().build());

        Mockito.when(commodityItemRepository.findAll()).thenReturn(expectedCommodities);

        //When
        List<CommodityItemResponseDto> resultCommodities = commodityItemServiceImpl.getAllCommodityItems();

        //Then
        assertThat(resultCommodities.size()).isEqualTo(expectedCommodities.size());

        for (CommodityItemResponseDto result: resultCommodities) {

            int index=resultCommodities.indexOf(result);
            CommodityItem expect = expectedCommodities.get(index);

            assertThat(result.getId()).isEqualTo(expect.getId());
            assertThat(result.getTitle()).isEqualTo(expect.getTitle());
            assertThat(result.getDescription()).isEqualTo(expect.getDescription());
            assertThat(result.getQuantity()).isEqualTo(expect.getQuantity());
        }
 
    }
}