package educationalproject.programmingstuff.data;

import educationalproject.programmingstuff.model.CommodityItem;
import educationalproject.programmingstuff.model.Item;
import educationalproject.programmingstuff.model.User;
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
                .quantity(DataGenerationCommon.getItemQuantity());

    }

    public Item.ItemBuilder getItemBuilder() {

        return Item.builder()
                .title(DataGenerationCommon.getUniqueItemTitle())
                .description(DataGenerationCommon.getItemDescription())
                .price(DataGenerationCommon.getItemPrice());

    }

    public User.UserBuilder getUserBuilder(){
        return User.builder()
                .name(DataGenerationCommon.getName())
                .surname(DataGenerationCommon.getSurname());
    }
}
