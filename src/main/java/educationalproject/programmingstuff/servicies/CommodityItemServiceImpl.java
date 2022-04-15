package educationalproject.programmingstuff.servicies;

import educationalproject.programmingstuff.model.CommodityItem;
import educationalproject.programmingstuff.repositories.CommodityItemRepository;
import educationalproject.programmingstuff.servicies.dto.CommodityItemResponseDto;
import educationalproject.programmingstuff.servicies.mappers.CommodityItemResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@RequiredArgsConstructor
@Validated
public class CommodityItemServiceImpl implements CommodityItemService {

    private final CommodityItemRepository commodityItemRepository;

    private final CommodityItemResponseMapper commodityItemResponseMapper;

    @Override
    public List<CommodityItemResponseDto> getAllCommodityItems() {
        List<CommodityItem> commodityItems = commodityItemRepository.findAll();
        return commodityItemResponseMapper.makeCommodityItemResponseOf(commodityItems);
    }

}
