package educationalproject.programmingstuff.servicies.mappers;

import educationalproject.programmingstuff.model.Item;
import educationalproject.programmingstuff.servicies.dto.StoredItemsResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StoredItemsResponseMapper {

    @Mapping(source = "id", target = "itemNumber")
    StoredItemsResponseDto makeStoredItemDtoOf(Item Item);

    List<StoredItemsResponseDto> makeStoredItemDtoOf(List<Item> Items);
}
