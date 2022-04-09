package educationalproject.programmingstuff.data;


import com.github.javafaker.Faker;
import educationalproject.programmingstuff.model.CommodityItem;
import educationalproject.programmingstuff.repositories.CommodityItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Random;

@Component
public class StoreHousePopulator {

    private final static String LANGUAGE = "ru";

    private final static String COUNTRY = "RU";

    private final static int SEED = 0;

    public final static int FAKE_COMMODITY_ITEMS_TOTAL = 50;

    private final Faker faker = new Faker(new Locale(LANGUAGE, COUNTRY), new Random(SEED));

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
                        CommodityItem.builder()
                        .title(faker.commerce().productName())
                        .description(getRandomShakespeareQuote())
                        .quantity(faker.random().nextInt(0,1000))
                        .build());
            }

            logger.info("Generated " + FAKE_COMMODITY_ITEMS_TOTAL + " fake commodity items");
        };

    }

    private String getRandomShakespeareQuote() {

        switch (faker.random().nextInt(0, 3)) {
            case 0:
                return faker.shakespeare().asYouLikeItQuote();
            case 1:
                return faker.shakespeare().hamletQuote();
            case 2:
                return faker.shakespeare().kingRichardIIIQuote();
            default:
                return faker.shakespeare().romeoAndJulietQuote();
        }

    }

}
