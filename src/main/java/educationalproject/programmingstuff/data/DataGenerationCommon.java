package educationalproject.programmingstuff.data;

import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.*;

@UtilityClass
public class DataGenerationCommon {

    final static String LANGUAGE = "en";

    final static String COUNTRY = "US";

    final static int SEED = 0;

    final static Faker faker = new Faker(new Locale(LANGUAGE, COUNTRY), new Random(SEED));

    private final static List<String> usedTitles = new ArrayList<>();

    private static final Map<String, Integer> titleIndexes = new HashMap<>();

    private final static int MIN_PRICE_IN_CENTS = 01;

    private final static int MAX_PRICE_IN_CENTS = 1_000_00;

    private final static int MIN_OF_QUANTITY_RANGE = 0;

    private final static int MAX_OF_QUANTITY_RANGE = 50;

    String getRandomItemDescription() {

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

    String getRandomUniqueItemTitle() {

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

    @NotNull BigDecimal getRandomItemPrice() {

        return BigDecimal.valueOf(
                (double) faker.random().nextInt(MIN_PRICE_IN_CENTS, MAX_PRICE_IN_CENTS) / 100.0);

    }

    Integer getRandomItemQuantity() {
        return faker.random().nextInt(MIN_OF_QUANTITY_RANGE, MAX_OF_QUANTITY_RANGE);
    }

    long getRandomItemNumber() {
        return faker.number().numberBetween(0L, Long.MAX_VALUE);
    }
}