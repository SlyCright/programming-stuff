package educationalproject.programmingstuff.data;

import com.github.javafaker.Faker;
import educationalproject.programmingstuff.model.CommodityItem;
import lombok.experimental.UtilityClass;

import java.util.Locale;
import java.util.Random;

@UtilityClass
public class CommodityItemGenerator {

    private final static String LANGUAGE = "ru";

    private final static String COUNTRY = "RU";

    private final static int SEED = 0;

    private final static int TOTAL_ITEMS_TO_GENERATE=50;

    final static Faker faker = new Faker(new Locale(LANGUAGE, COUNTRY), new Random(SEED));

    public CommodityItem.CommodityItemBuilder getCommodityItemBuilder(){

        return CommodityItem.builder()
                .title(faker.commerce().productName())
                .description(getRandomShakespeareQuote())
                .quantity(faker.random().nextInt(0,TOTAL_ITEMS_TO_GENERATE));
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
