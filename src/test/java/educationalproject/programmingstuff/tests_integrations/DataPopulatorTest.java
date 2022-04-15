package educationalproject.programmingstuff.tests_integrations;

import educationalproject.programmingstuff.data.DataPopulator;
import educationalproject.programmingstuff.repositories.CommodityItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DataPopulatorTest {

    @Autowired
    DataPopulator dataPopulator;

    @Autowired
    CommodityItemRepository commodityItemRepository;

    @Autowired
    CommodityItemRepository itemRepository;

    @Test
    void givenSpringContextStarted_whenCheckIfDataTablesPopulated_thenSuccess() {

        assertThat(itemRepository.count()).isEqualTo(DataPopulator.ITEMS_TOTAL_TO_GENERATE);

        assertThat(commodityItemRepository.count()).isEqualTo(DataPopulator.ITEMS_TOTAL_TO_GENERATE);

    }

}