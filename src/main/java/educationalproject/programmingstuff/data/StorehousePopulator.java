package educationalproject.programmingstuff.data;


import educationalproject.programmingstuff.repositories.CommodityItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorehousePopulator {

    public final static int FAKE_COMMODITY_ITEMS_TOTAL = 100;

    @Bean
    public CommandLineRunner loadData(CommodityItemRepository commodityItemRepository) {

        return args -> {

            Logger logger = LoggerFactory.getLogger(getClass());

            if (commodityItemRepository.count() != 0L) {
                logger.info("Using existing database");
                return;
            }

            for (int i = 0; i < FAKE_COMMODITY_ITEMS_TOTAL; i++) {
                commodityItemRepository.saveAndFlush(
                        CommodityItemGenerator.getCommodityItemBuilder().build());
            }

            logger.info("Generated " + FAKE_COMMODITY_ITEMS_TOTAL + " fake commodity items");
        };
    }

}
