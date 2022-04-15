package educationalproject.programmingstuff.data;

import com.github.javafaker.Faker;
import educationalproject.programmingstuff.model.CommodityItem;
import educationalproject.programmingstuff.model.Item;
import lombok.experimental.UtilityClass;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.*;

@UtilityClass
public class EntitiesGenerator {

    private final static String LANGUAGE = "en";

    private final static String COUNTRY = "US";

    private final static int SEED = 0;

    private final static Faker faker = new Faker(new Locale(LANGUAGE, COUNTRY), new Random(SEED));

    private final static int MIN_OF_QUANTITY_RANGE = 0;

    private final static int MAX_OF_QUANTITY_RANGE = 50;

    private final static List<String> usedTitles = new ArrayList<>(MAX_OF_QUANTITY_RANGE);

    private static final Map<String, Integer> titleIndexes = new HashMap<>();

    private final static int MIN_PRICE_IN_CENTS = 01;

    private final static int MAX_PRICE_IN_CENTS = 1_000_00;

    public CommodityItem.CommodityItemBuilder getCommodityItemBuilder() {

        return getCommodityItemBuilder(getItemBuilder().build());

    }

    public CommodityItem.CommodityItemBuilder getCommodityItemBuilder(@NotNull Item item) {

        return CommodityItem.builder()
                .item(item)
                .quantity(faker.random().nextInt(MIN_OF_QUANTITY_RANGE, MAX_OF_QUANTITY_RANGE));

    }

    public Item.ItemBuilder getItemBuilder() {

        String title = getUniqueTitle();

        return Item.builder()
                .title(title)
                .description(getRandomShakespeareQuote())
                .price(
                        BigDecimal.valueOf(
                                (double) faker.random().nextInt(MIN_PRICE_IN_CENTS, MAX_PRICE_IN_CENTS) / 100.0));

    }

    private String getUniqueTitle() {

        String title = faker.commerce().productName();

        if (usedTitles.contains(title)) {

            Integer currentIndex = titleIndexes.get(title);
            currentIndex = currentIndex == null ? 2 : currentIndex + 1;

            titleIndexes.put(title, currentIndex);

            title+= " v."+currentIndex;

        }

        usedTitles.add(title);

        return title;
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
