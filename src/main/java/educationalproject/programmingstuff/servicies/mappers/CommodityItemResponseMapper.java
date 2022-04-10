package educationalproject.programmingstuff.servicies.mappers;


import educationalproject.programmingstuff.model.CommodityItem;
import educationalproject.programmingstuff.servicies.dto.CommodityItemResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommodityItemResponseMapper {

    CommodityItemResponseDto makeCommodityItemResponseOf(CommodityItem commodityItem);

    List<CommodityItemResponseDto> makeCommodityItemResponseOf(List<CommodityItem> commodityItems);

}
