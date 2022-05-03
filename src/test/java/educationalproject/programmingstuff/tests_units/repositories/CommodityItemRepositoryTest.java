package educationalproject.programmingstuff.tests_units.repositories;

import educationalproject.programmingstuff.model.CommodityItem;
import educationalproject.programmingstuff.repositories.CommodityItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

//@DataJpaTest //todo move to integration test. Read how to fill DB
@SpringBootTest
class CommodityItemRepositoryTest {

    @Autowired
    CommodityItemRepository commodityItemRepository;

    @Test
    void getAllCommodityItemsBoundWithItems() {
//        List<String> outputStrings = commodityItemRepository.getAllCommodityItemsBoundWithItems();
//        System.out.println(outputStrings);
        List<CommodityItem> ci = commodityItemRepository.getAllCommodityItemsWithFetchedItems();
        //   System.out.println("ci = " + ci);
        System.out.println();
    }
}