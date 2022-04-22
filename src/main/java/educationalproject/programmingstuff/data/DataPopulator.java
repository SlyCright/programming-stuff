package educationalproject.programmingstuff.data;


import educationalproject.programmingstuff.model.CommodityItem;
import educationalproject.programmingstuff.model.Item;
import educationalproject.programmingstuff.repositories.CommodityItemRepository;
import educationalproject.programmingstuff.repositories.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataPopulator {

    public final static int ITEMS_TOTAL_TO_GENERATE = 10;

    @Bean
    public CommandLineRunner loadData(
            ItemRepository itemRepository,
            CommodityItemRepository commodityItemRepository
    ) {

        return args -> {

            Logger logger = LoggerFactory.getLogger(getClass());

            if (commodityItemRepository.count() != 0L) {
                logger.info("Using existing database");
                return;
            }

            for (int i = 0; i < ITEMS_TOTAL_TO_GENERATE; i++) {

                Item fakeItem = EntitiesGenerator.getItemBuilder().build();
                itemRepository.save(fakeItem);

                CommodityItem fakeCommodityItem = EntitiesGenerator.getCommodityItemBuilder(fakeItem).build();
                commodityItemRepository.save(fakeCommodityItem);
            }

            logger.info("Generated " + ITEMS_TOTAL_TO_GENERATE + " fake commodity items");
        };
    }

}
