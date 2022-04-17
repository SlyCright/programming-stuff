package educationalproject.programmingstuff.data;

import educationalproject.programmingstuff.model.CommodityItem;
import educationalproject.programmingstuff.model.Item;
import lombok.experimental.UtilityClass;

import javax.validation.constraints.NotNull;

@UtilityClass
public class EntitiesGenerator {

    public CommodityItem.CommodityItemBuilder getCommodityItemBuilder() {

        return getCommodityItemBuilder(getItemBuilder().build());

    }

    public CommodityItem.CommodityItemBuilder getCommodityItemBuilder(@NotNull Item item) {

        return CommodityItem.builder()
                .item(item)
                .quantity(DataGenerationCommon.getRandomItemQuantity());

    }

    public Item.ItemBuilder getItemBuilder() {

        return Item.builder()
                .title(DataGenerationCommon.getRandomUniqueItemTitle())
                .description(DataGenerationCommon.getRandomItemDescription())
                .price(DataGenerationCommon.getRandomItemPrice());

    }

}
