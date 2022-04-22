package educationalproject.programmingstuff.servicies.mappers;

import educationalproject.programmingstuff.model.CommodityItem;
import educationalproject.programmingstuff.servicies.dto.StoredItemsResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StoredItemsMapper {

    @Deprecated
    @Mapping(target = "itemNumber", source = "id")
    @Mapping(target = "title", source = "item.title")
    @Mapping(target = "description", source = "item.description")
    @Mapping(target = "price", source = "item.price")
    StoredItemsResponseDto makeStoredItemDtoOf(CommodityItem commodityItem);

    @Deprecated
    List<StoredItemsResponseDto> makeStoredItemDtoOf(List<CommodityItem> commodityItems);
}
