package educationalproject.programmingstuff.tests_integrations;

import educationalproject.programmingstuff.data.StorehousePopulator;
import educationalproject.programmingstuff.repositories.CommodityItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static educationalproject.programmingstuff.data.StorehousePopulator.FAKE_COMMODITY_ITEMS_TOTAL;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class StorehousePopulatorTest {

    @Autowired
    StorehousePopulator storeHousePopulator;

    @Autowired
    CommodityItemRepository commodityItemRepository;

    @Test
    void givenSpringContextStarted_whenCheckIfStorehousePopulated_thenSuccess() {

        assertThat(commodityItemRepository.count()).isEqualTo(FAKE_COMMODITY_ITEMS_TOTAL);

    }

}