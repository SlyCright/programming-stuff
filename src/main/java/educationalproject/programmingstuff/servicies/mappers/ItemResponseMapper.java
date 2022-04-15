package educationalproject.programmingstuff.servicies.mappers;


import educationalproject.programmingstuff.model.Item;
import educationalproject.programmingstuff.servicies.dto.ItemResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemResponseMapper {

    ItemResponseDto makeItemResponseOf(Item Item);

    List<ItemResponseDto> makeItemResponseOf(List<Item> Items);

}
